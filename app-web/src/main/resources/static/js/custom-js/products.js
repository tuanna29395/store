$(document).ready(function () {
    window.onload = function () {
        changeBackgroundMenuCategory();
    };
    changePageSize();
    renderSizeOption();
    onclickSelectStar();
});

function onclickSelectStar() {
    $('.number-start').on('click', function () {
        $(this).find("i").addClass('start-color');
    })
}

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

function changePageSize() {
    $(".page-size").on('change', function () {
        let currentPageSize = getUrlParameter('size')

        let newPageSize = $('.page-size').val();
        let url = window.location.href;
        if (currentPageSize) {
            url = url.replace("size=" + currentPageSize, "size=" + newPageSize);
        } else {
            if (url.includes('=')) {
                url += "&size=" + newPageSize;
            } else {
                url += "?size=" + newPageSize;
            }

        }

        window.location = url;
    })

}

function getUrlParameter(sParam) {
    let sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
}

function renderSizeOption() {
    $.ajax({
        url: "/api/sizes",
        type: "GET",
        success: function (response) {
            $('.size-option').empty();
            response.forEach(function (item) {
                let data = {}
                data.sizeId = item.id;
                data.sizeName = item.name;

                $("#size-option-item").tmpl(data).appendTo(".size-option");
            })
        }
    })
}