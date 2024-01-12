$(document).ajaxSend(function(event, xhr, ajaxOption) {
    $('body .loading-container-wrap').removeClass('hide');
});

$(document).ajaxStop(function(data) {
    $('body .loading-container-wrap').addClass('hide');
});

CHOI = {
    ajax: function(method, url, contentType, data) {
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

            try {
                $.ajax(ajaxOption);
            } catch (error) {
                reject(error);
            }
        });
    },
    get: function(url, contentType, data) {
        return this.ajax('GET', url, contentType, data);
    },
    post: function(url, contentType, data) {
        return this.ajax('POST', url, contentType, data);
    },
    put: function(url, contentType, data) {
        return this.ajax('PUT', url, contentType, data);
    },
    delete: function(url, data, submit, contentType) {
        return this.ajax('DELETE', url, contentType, data);
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