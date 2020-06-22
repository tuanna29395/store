$(document).ready(function () {
    renderCategories();
    renderProductBestSelling();
});

function renderCategories() {
    $.ajax({
        url: "/api/categories",
        type: "GET",
        success: function (response) {
            response.forEach(function (item) {
                $("#item-category").tmpl(item).appendTo("#content-categories");
            })
        }
    })
}

function renderProductBestSelling() {
    $.ajax({
        url: "/api/product/best-selling",
        type: "GET",
        success: function (response) {
            response.forEach(function (item) {
                $("#product-best-selling").tmpl(transformProductBestSelling(item)).appendTo(".recent-product");
            })
        }
    });
}

function transformProductBestSelling(item) {
    let data = {};
    let imageUrl = "/images/product/" + item.imageUrl;
    data.id = item.id;
    data.imageUrl = imageUrl;
    data.name = item.name;
    let salePrice = item.salePrice;
    data.originalPrice = item.salePrice;
    let styleHideOriginalPrice = "hide-original-price";
    if (item.isDiscount) {
        salePrice = item.discountPrice;
        styleHideOriginalPrice = "";
    }
    data.styleHideOriginalPrice = styleHideOriginalPrice;
    data.salePrice = salePrice;
    return data;
}