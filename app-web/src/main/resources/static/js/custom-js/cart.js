$(document).ready(function () {
    showAllCartItem();
    onclickDeleteCartItem();
    onclickUpdateCart();
});

function showAllCartItem() {
    $.ajax({
        url: "/api/carts",
        type: "GET",
        success: function (response) {
            $(".content-item-cart").empty();
            let totalAmount = 0;
            response.forEach(function (item) {
                totalAmount += parseInt(item.amount.replace(',',""));
                fillCartItem(item);
            })
            totalAmount = new Intl.NumberFormat('vn', { style: 'currency', currency: 'VND' }).format(totalAmount);
            showTotalAmountCart(totalAmount);
        }
    });
}

function renderSizeOption(cartItem) {

    $.ajax({
        url: "/api/sizes",
        type: "GET",
        success: function (response) {
            let classAppendTo = "";
            response.forEach(function (item) {
                let data = {};
                data.sizeId = item.id;
                data.sizeName = item.name;
                data.sizePrice = item.price;
                classAppendTo = `.size-option-${cartItem.productId}-${cartItem.sizeId}`;
                $("#size-option-item-cart").tmpl(data).appendTo(classAppendTo);
            })
            $(`${classAppendTo} option[value='${cartItem.sizeId}']`).attr('selected', 'selected');
        }
    })
}

function onclickDeleteCartItem() {
    $(".content-item-cart").on('click', '.remove-cart-item', function () {
        let productSizeIds = convertStringToArray($(this).data('cart-id'));
        let data = {
            productId: productSizeIds[0],
            sizeId: productSizeIds[1]
        };
        confirmWarning({message: "Bạn có muốn xóa sản phẩm khỏi giỏ hàng không?"}, function () {
            callAjaxDeleteItem(data);
        });

    })
}

function callAjaxDeleteItem(data) {
    $.ajax({
        method: 'POST',
        url: '/api/carts/delete',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            $('.processing').show();
            showAllCartItem();
            renderCartShoppingHeader();
        }
    });
}

function fillCartItem(data) {
    let item = convertToCartItemData(data);
    $("#item-cart-list").tmpl(item).appendTo(".content-item-cart");
    renderSizeOption(item);
}

function onclickUpdateCart() {
    $(".content-item-cart").on('click', '.update-cart-item', function () {
        let productSizeIds = convertStringToArray($(this).data('cart-id'));
        let productId = productSizeIds[0];
        let sizeIdOld = productSizeIds[1];
        let sizeIdNew = $(`.item-${productId}-${sizeIdOld}`).find('.size-option').val();
        let quantity = $(`.item-${productId}-${sizeIdOld}`).find('.quantity').val();
        let data = {
            productId: productId,
            sizeIdOld: sizeIdOld,
            sizeIdNew: sizeIdNew,
            quantity: quantity,
        };

        callAjaxUpdateCartItem(data)

    })

}

function callAjaxUpdateCartItem(data) {
    $.ajax({
        method: 'POST',
        url: '/api/carts/update',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            $('.processing').show();
            showAllCartItem();
            renderCartShoppingHeader();
        }
    })

}

function showTotalAmountCart(totalAmount) {
$(".total-amount").text(totalAmount);
}