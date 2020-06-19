const PATH_IMAGE = "/images/product/";
$(document).ready(function () {
    renderCartShoppingHeader();
    onclickRemoveItemCartHeader();
});

function renderCartShoppingHeader() {
    $.ajax({
        url: "/api/carts",
        type: "GET",
        success: function (response) {
            $(".total-cart").remove();
            $(".item-cart").empty();
            let item = {};
            let total = response.length;
            if (!total) total = 0;
            item.total = total;
            let totalAmount = 0;
            $("#total-item-cart").tmpl(item).appendTo(".my-cart");
            response.forEach(function (item) {
                totalAmount += parseInt(item.amount.replace(',',""));
                $("#item-cart").tmpl(convertToCartItemData(item)).appendTo(".item-cart");
            });
            item.totalAmount = new Intl.NumberFormat('vn', { style: 'currency', currency: 'VND' }).format(totalAmount);
            $("#total-price-cart").tmpl(item).appendTo(".item-cart");
        }
    })
}

function onclickRemoveItemCartHeader() {
    $('.item-cart').on('click', '.remove-cart-item', function () {
        let productSizeIds = convertStringToArray($(this).data('cart-id'));
        let data = {
            productId: productSizeIds[0],
            sizeId: productSizeIds[1]
        };
        callAjaxRemoveItemCarHeader(data);
    })
}

function callAjaxRemoveItemCarHeader(data) {
    $.ajax({
        method: 'POST',
        url: '/api/carts/delete',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            $('.processing').show();
            renderCartShoppingHeader();
        }
    });
}

function convertToCartItemData(dataOriginal) {
    let data = {};
    let productPrice = dataOriginal.product.salePrice;
    if (dataOriginal.product.isDiscount){
        productPrice = dataOriginal.product.discountPrice;
    }
    data.imageUrl = PATH_IMAGE + dataOriginal.product.imageUrl;
    data.productName = dataOriginal.product.name;
    data.productPrice = productPrice;
    data.quantity = dataOriginal.quantity;
    data.amount = dataOriginal.amount;
    data.sizeName = dataOriginal.size.name;
    data.sizePrice = dataOriginal.size.price;
    data.productId = dataOriginal.product.id;
    data.sizeId = dataOriginal.size.id;
    data.removeItemId = `.size-option-${data.productId}-${data.sizeId}`
    return data;
}

function convertStringToArray(data) {
    return data.split(',').map(function (item) {
        return parseInt(item, 10);
    });
}