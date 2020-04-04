$.i18n({
    locale: document.documentElement.lang,
})
.load({
        'en': '/vendor/jquery-validation/i18n/message/message_en.json',
        'jp': '/vendor/jquery-validation/i18n/message/message_jp.json',
})
.done( ()=>{
    if (typeof validateLoginForm !== 'undefined')
            validateLoginForm();
});