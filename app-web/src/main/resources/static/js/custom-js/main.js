const PATH_IMAGE = "/images/product/";
$(document).ready(function () {
    renderCartShopping();
});

function renderCartShopping() {
    $.ajax({
        url: "/api/cart",
        type: "GET",
        success: function (response) {
            $(".total-cart").remove();
            let item = {};
            let total = response.length;
            if (!total) total = 0;
            item.total = total;
            let totalAmount = 0;
            $("#total-item-cart").tmpl(item).appendTo(".my-cart");
            response.forEach(function (item) {
                totalAmount += item.amount;
                $("#item-cart").tmpl(convertToCartItemData(item)).appendTo(".item-cart");
            });
            item.totalAmount = totalAmount;
            $("#total-price-cart").tmpl(item).appendTo(".item-cart");
        }
    })
}

function convertToCartItemData(dataOriginal) {
    let data = {};
    data.imageUrl = PATH_IMAGE + dataOriginal.product.imageUrl;
    data.productName = dataOriginal.product.name;
    data.productPrice = dataOriginal.product.salePrice;
    data.quantity = dataOriginal.quantity;
    data.amount = dataOriginal.amount;
    data.sizeName = dataOriginal.size.name;
    data.sizePrice = dataOriginal.size.price;
    data.productId = dataOriginal.product.id;
    data.sizeId = dataOriginal.size.id;
    return data;
}
