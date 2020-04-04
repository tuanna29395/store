$(document).ready(function () {

});

function validateLoginForm() {
    let emailRule = {
        required: true,
        maxLength: 50,
        normalizer: function (value) {
            this.value = $.trim(value);
            return this.value;
        },
    };

    $('#form-login').validate({
        ignore: [],
        onkeyup: false,
        rules: {
            email: emailRule,
        },
        messages: {
            email: {
                required: $.i18n('field_required', 'Theme name'),
                maxlength: $.i18n('field_over_length', 'less than 50', 'email'),
            }
        },
        highlight: function (element) {
            $(element).addClass('box-error');
        },
        unhighlight: function (element) {
            $(element).removeClass('box-error');
        },
    });
}