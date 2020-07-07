$(document).ready(function () {
    renderSizeOption();
    onclickSelectStar();
    getReviews();
});
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
function transformReviewItem(item) {
    let data = {};
    data.userName = item.user.username;
    data.createdAt = moment(item.createdAt).format(DATE_PATTERN);

    data.content = item.reviewContent;
    data.numberStar = new Array(item.numberStar);

    return data;
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

