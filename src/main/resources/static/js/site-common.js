$(document).ajaxSend(function(event, xhr, ajaxOption) {
    $('body .loading-container-wrap').removeClass('hide');
});

$(document).ajaxStop(function(data) {
    $('body .loading-container-wrap').addClass('hide');
});

CHOI = {
    viewHandler: function(url, param, done, errDone) {
        $.ajax({
            url: '/view',
            type: 'post',
            data: { view:url, ...param },
            success: function(viewResult) {
                $('body').html(viewResult);
                if(done) done();
            },
            error: function(err) {
                if(errDone) errDone(err);
            }
        });
    },
    ajax: function(url, method, data, submit, contentType) {
        return new Promise(function (resolve, reject) {
            if(contentType === '' || contentType === undefined || contentType === null) contentType = 'application/x-www-form-urlencoded';

            var ajaxOption = {
                url : url,
                type : method,
                data : data,
                contentType : contentType,
                // beforeSend: function(xhr) {
                //     if(!submit && (method == 'POST' || method == 'PUT' || method == 'DELETE')) xhr.setRequestHeader('Content-type', 'application/json');
                // },
                success : resolve,
                error : reject
            };

            if(submit) {
                ajaxOption['processData'] = false;
                ajaxOption['contentType'] = false;
            } else if(!submit && (method == 'POST' || method == 'PUT'|| method == 'DELETE')) {
                ajaxOption.data = JSON.stringify(ajaxOption.data);
            }

            try {
                $.ajax(ajaxOption);
            } catch (error) {
                reject(error);
            }
        });
    },
    get: function(url, dataObject) {
        return this.ajax(url, 'GET', dataObject)
    },
    post: function(url, dataObject, submit, contentType) {
        return this.ajax(url, 'POST', dataObject, submit, contentType);
    },
    put: function(url, dataObject, submit, contentType) {
        return this.ajax(url, 'PUT', dataObject, submit, contentType);
    },
    delete: function(url, dataObject, submit, contentType) {
        return this.ajax(url, 'DELETE', dataObject, submit, contentType);
    },
    postSubmit: function(url, formData) {
        return this.ajax(url, 'POST', formData,true);
    },
    putSubmit: function(url, formData) {
        return this.ajax(url, 'PUT', formData, true);
    },
    keyReplace: function(el, param) {
        var target = el instanceof jQuery ? el:$(el);
        var obj = {
            regexp: null,
            length: null,
            replaceText: '',
        };

        if(Object.keys(param).length) {
            $.extend(obj, param);
        } else {
            obj.regexp = param;
        }

        target.on('keyup', function() {
            $(this).val($(this).val().replace(obj.regexp, (obj.replaceText ? obj.replaceText:'')));
            if(obj.length) $(this).val($(this).val().slice(0, obj.length));
        });
    },
    blurWarning: function(el, param, done) {
        var target = el instanceof jQuery ? el:$(el);
        var obj = {
            regexp: null,
            regFunction: null,
            length: null,
            warningText: '값을 입력해주세요',
        };

        if(Object.keys(param).length) {
            $.extend(obj, param);
        } else {
            obj.regexp = param;
        }

        target.on('blur', function(e) {
            var _value = $(this).val();

            if(obj.regexp) {
                var _regexp = (typeof obj.regexp == 'string' ? new RegExp(obj.regexp, 'g'):obj.regexp);
                var _test = _regexp.test(_value);

                if(!target.val()) {
                    $(this).next('span.warning').hide();
                } else if(_test) {
                    $(this).next('span.warning').hide();
                } else {
                    if($(this).next('span.warning').length) {
                        $(this).next('span.warning').show();
                    } else {
                        var _warning = $('<span class="warning">');
                        _warning.html(obj.warningText);
                        $(this).after(_warning);
                    }
                }

                if(typeof obj.regFunction == 'function') {
                    obj.regFunction(el, _test);
                }
            }

            if(obj.length) {
                if(obj.length < _value.length) {
                    if($(this).next('span.warning').length) {
                        $(this).next('span.warning').show();
                    } else {
                        var _warning = $('<span class="warning">');
                        _warning.html(obj.warningText);
                        $(this).after(_warning);
                    }
                } else {
                    $(this).next('span.warning').hide();
                }
            }

            if(typeof done == 'function') done();
        });
    },
    convertForm: function(el) {
        var fd = new FormData();
        $.each(el.find('[name]'), function() {
            if(this.type == 'file') {
                fd.append(this.name, $(this)[0].files[0]);
            } else {
                fd.append(this.name, this.value);
            }
        });

        return fd;
    },
    convertObject: function(el) {
        var obj = {};
        $.each(el.find('[name]'), function() {
            obj[this.name] = this.value;
        });

        return obj;
    },
    convertObjectToForm: function(obj) {
        var _fd = new FormData();
        $.each(obj, function(k, v) { _fd.append(k, v); });

        return _fd;
    },
    resMessage: function(response, callback) {
        if(typeof response != 'object') {
            console.error('[ XE.resMessage ] Response parameter type is strange');
            return false;
        } else if(typeof callback != 'object') {
            if(typeof callback == 'function') {
                callback = { success:callback };
            } else {
                console.error('[ XE.resMessage ] Callback parameter type is strange');
                return false;
            }
        }

        var res = {
            result: null,
            message: null,
            value: null
        }

        var cb = {
            success: function(){},
            fail: function(){},
            other: function(res){ alert(res.message); },
            done: function(){},
        }

        $.extend(res, response);
        $.extend(cb, callback);

        if(res.result == '0000') {
            cb.success(res.value);
        } else if(res.result == '9999') {
            alert(res.message);
        } else {
            cb.other(res);
        }

        cb.done(res);
    },
    getUrlObject: function() {
        var obj = {};
        if(location.search) {
            $.each(location.search.slice(1).split('&'), function(key, value) {
                if(value) {
                    var _split = value.split('=');
                    var _index = value.indexOf('=') + 1;
                    var _value = value.slice(_index);
                    obj[_split[0]] = decodeURI(window.atob(_value));

                    if(_split[0] == 'toast') {
                        var _search = location.search;
                        _search = _search.replace(value + '&', '');
                        history.replaceState({}, null, location.pathname + (_search == '?' ? '':_search));
                    }
                }
            });
        }

        return obj;
    },
    getParameterByName: function(name) {
        var _name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + _name + "=([^&#]*)");
        var results = regex.exec(location.search);

        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    },
    location: function(url, params) {
        var _params = '?';
        if(!params) {

        } else if(typeof params == 'object') {
            Object.keys(params).forEach(function(v) {
                _params += v + '=' + window.btoa(encodeURI(params[v])) + '&';
            });
        } else {
            console.error('[ XE.location ] Params parameter type is strange');
            return false;
        }

        location.href = url + (_params == '?' ? '':_params);
    },
    format: function(target, format) {
        if(!target) {
            console.error('[ XE.format ] Target parameter is null');
            return false;
        } else if(!format) {
            console.error('[ XE.format ] Format parameter is null');
            return false;
        }

        var _format = null;
        if(format == 'phone') _format = this.phoneFm;

        if(typeof target == 'string') {
            if(format == 'phone') return _format(target);
        } else if(typeof target == 'object') {
            target.on('keyup', function() {
                $(this).val(_format(this.value));
            });
        } else {
            console.error('[ XE.format ] Response parameter type is target');
        }
    },
    phoneFm: function(value, hideFlag) {
        var formatNum = '';
        var num = value ? value.replace(/[^0-9]/g, ''):'';

        if(num) {
            if(num.length == 6) {
                formatNum = num.replace(/(\d{3})(\d{3})/, '$1-$2');
            } else if(num.length == 7) {
                formatNum = num.replace(/(\d{3})(\d{4})/, '$1-$2');
            } else if(num.length == 8) {
                formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
            } else if(num.length == 9) {
                formatNum = num.replace(/(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
            } else if(num.length == 10) {
                if(hideFlag) formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-****-$3');
                else{
                    if(num.indexOf('02') == 0) formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
                    else formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
                }
            } else if(num.length == 11) {
                if(hideFlag) formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
                else formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            } else if(num.length == 12) {
                if(hideFlag) formatNum = num.replace(/(\d{4})(\d{4})(\d{4})/, '$1-****-$3');
                else formatNum = num.replace(/(\d{4})(\d{4})(\d{4})/, '$1-$2-$3');
            } else if(num.indexOf('02') == 0) {
                if(hideFlag) formatNum = num.replace(/(\d{2})(\d{3})(\d{4})/, '$1-****-$3');
                else formatNum = num.replace(/(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
            } else {
                formatNum = num;
            }

            return formatNum;
        } else return num;
    },
    numberFormat: function (value) {
        return new Intl.NumberFormat().format(value);
    },

    blockScroll: function () {
        $('body').css({ overflow: 'hidden' });
    },
    allowScroll: function () {
        $('body').css({ overflow: 'auto' });
    },

    byteToHumanReadableFormat: function (value) {
        var kbSize = value / 1024;
        var mbSize = kbSize / 1024;
        var gbSize = mbSize / 1024;

        var sizeText = value + 'BYTE';
        if (kbSize > 1) {
            sizeText = kbSize.toFixed(1) + 'KB';
        }

        if (mbSize > 1) {
            sizeText = mbSize.toFixed(1) + 'MB';
        }

        if (gbSize > 1) {
            sizeText = gbSize.toFixed(1) + 'GB';
        }

        return sizeText;
    },
    htmlEscape: function (text) {
        if (!text) {
            return '';
        }

        return text.replaceAll('<', '&lt;')
            .replaceAll('>', '&gt;')
            .replaceAll('\r\n', '<br>')
            .replaceAll('\n', '<br>')
            .replaceAll('&', '&amp;');
    },
    isBlank: function (text) {
        let trimText = text.trim();
        return trimText == '' || trimText == null || trimText == undefined;
    },
    isNotBlank: function (text) {
        let trimText = text.trim();
        return trimText != '' || trimText != null || trimText != undefined;
    }
}