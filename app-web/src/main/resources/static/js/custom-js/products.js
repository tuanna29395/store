$(document).ready(function () {
    window.onload = function () {
        changeBackgroundMenuCategory();
    };
    changePageSize();
    changeSortBy();
    // onclickAddReview();
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

function changeSortBy() {
    $(".sort-by").on('change', function () {
        let currentSortBy = getUrlParameter('sortBy')
        let newSortBy = $('.sort-by').val();
        let url = window.location.href;
        if (currentSortBy) {
            url = url.replace("sortBy=" + currentSortBy, "sortBy=" + newSortBy);
        } else {
            if (url.includes('=')) {
                url += "&sortBy=" + newSortBy;
            } else {
                url += "?sortBy=" + newSortBy;
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