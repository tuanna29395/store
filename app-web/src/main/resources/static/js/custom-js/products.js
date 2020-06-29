$(document).ready(function () {
    window.onload = function () {
        changeBackgroundMenuCategory();
    };
    changePageSize();
    renderSizeOption();
    onclickSelectStar();
    getReviews();
    // onclickAddReview();
});

function onclickSelectStar() {
    $('.number-star').on('click', function () {
        let numberStar = $(this).data("star");

        $(".your-rating").find(".number-star i").each(function () {
            $(this).removeClass('color-red');
            $(this).addClass('color-star');
        });

        $(this).find("i").removeClass('color-star');
        $(this).find("i").addClass('color-red');
        $("#star").val(numberStar);
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
                let data = {};
                data.sizeId = item.id;
                data.sizeName = item.name;

                $("#size-option-item").tmpl(data).appendTo(".size-option");
            })
        }
    })
}

function getReviews() {
    let productId = $("#product-id").val();
    $.ajax({
        url: `/review/${productId}/reviews`,
        type: "GET",
        async: false,
        success: function (response) {
            $(".review-content").empty();
            response.content.forEach(function (item) {
                let data = transformReviewItem(item)
                $("#item-review").tmpl(data).appendTo(".review-content");
            });
            $("#number-review").text(response.content.length);
        }
    })
}

function transformReviewItem(item) {
    let data = {};
    data.userName = item.user.username;
    data.createdAt = moment(item.createdAt).format(DATE_PATTERN);

    data.content = item.reviewContent;
    data.numberStar = new Array(item.numberStar);

    return data;
}

function onclickAddReview() {
    $('#add-review').on('click', function () {
        callAjaxAddReview();
    })

}

function callAjaxAddReview() {
    let productId = $("#product-id").val();
    let numberStar = $("#star").val();
    let reviewContent = $("#product-message").val();
    let data = {
        numberStar: numberStar,
        reviewContent: reviewContent
    };
    $.ajax({
        url: `/review/${productId}/add`,
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            $("#item-review").tmpl(transformReviewItem(response)).prepend(".review-content");

        }
    })
}