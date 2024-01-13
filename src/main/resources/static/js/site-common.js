$(document).ajaxSend(function(event, xhr, ajaxOption) {
    $('body .loading-container-wrap').removeClass('hide');
});

$(document).ajaxStop(function(data) {
    $('body .loading-container-wrap').addClass('hide');
});

CHOI = {
    ajax: function(method, url, data, contentType) {
        return new Promise(function (resolve, reject) {
            if(contentType === '' || contentType === undefined || contentType === null) contentType = 'application/x-www-form-urlencoded';

            var ajaxOption = {
                url : url,
                type : method,
                contentType : contentType,
                data : data,
                success : resolve,
                error : reject
            };

            if(contentType === 'file') {
                ajaxOption = {
                    url : url,
                    type : method,
                    processData : false,
                    contentType : false,
                    data : data,
                    success : resolve,
                    error : reject
                };
            }

            try {
                $.ajax(ajaxOption);
            } catch (error) {
                reject(error);
            }
        });
    },
    get: function(url, data, contentType) {
        return this.ajax('GET', url, data, contentType);
    },
    post: function(url, data, contentType) {
        return this.ajax('POST', url, data, contentType);
    },
    put: function(url, data, contentType) {
        return this.ajax('PUT', url, data, contentType);
    },
    delete: function(url, data, contentType) {
        return this.ajax('DELETE', url, data, contentType);
    },
    blockScroll: function () {
        $('body').css({ overflow: 'hidden' });
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