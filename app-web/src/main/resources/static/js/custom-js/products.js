$(document).ready(function () {
    window.onload = function () {
        changeBackgroundMenuCategory();
    }
});

function getUrlParamCategory() {
    let hash, categoryId;
    let hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    hash = hashes[0].split('=');
    categoryId = hash[1];
    return categoryId;

}

function changeBackgroundMenuCategory() {
    let categoryId = getUrlParamCategory();

    if (categoryId) {
        $('#content-categories li').each(function () {
            let idElement = 'category-' + categoryId;
            if ($(this).attr('id') === idElement) {
                $(this).addClass('bg-category');
            }
        });

    }
}