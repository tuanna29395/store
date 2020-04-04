function baseConfirm(data, callback, cancelCallback) {
    if (typeof callback === 'undefined' && !$.isFunction(callback)) {
        return false;
    }

    data.title = '';
    data.text = data.message;
    data.button = data.confirmText;
    data.buttons = {
        cancel: {
            text: $.i18n('btn_cancel'),
            visible: true,
        },
        confirm: {
            text: $.i18n('btn_ok'),
            visible: true,
        }
    };

    swal(data).then(iscomfirm => {
        if (iscomfirm) {
            callback();
        } else if (typeof cancelCallback === 'function') {
            cancelCallback()
        }
    });
}

function baseAlert(data, callback = null) {
    data.content = data.content;
    data.title = '';
    data.text = data.message;
    data.button = $.i18n('btn_ok');
    data.allowOutsideClick = true;
    data.html = true;
    swal(data).then(callback);
}

function confirmInfo(data, callback, cancelCallback) {
    data.icon = 'info';
    data.confirmButtonClass = 'btn-info';
    baseConfirm(data, callback, cancelCallback);
}

function confirmWarning(data, callback) {
    data.icon = 'warning';
    data.confirmButtonClass = 'btn-warning';
    baseConfirm(data, callback);
}

function confirmDanger(data, callback) {
    data.icon = 'error';
    data.confirmButtonClass = 'btn-danger';
    baseConfirm(data, callback);
}

function alertSuccess(data, callback = null) {
    data.icon = 'success';
    data.confirmButtonClass = "btn-success";
    baseAlert(data, callback);
}

function alertInfo(data, callback = null) {
    data.icon = 'info';
    data.confirmButtonClass = 'btn-info';
    baseAlert(data, callback);
}

function alertWarning(data, callback = null) {
    data.icon = 'warning';
    data.confirmButtonClass = 'btn-warning';
    baseAlert(data, callback);
}

function alertDanger(data, callback = null) {
    data.icon = 'error';
    data.confirmButtonClass = 'btn-danger';
    baseAlert(data, callback);
}