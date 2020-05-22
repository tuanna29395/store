$(document).ready(function () {
    showAllCartItem();
    //renderSizeOption();
});

function showAllCartItem() {
    $.ajax({
        url: "/api/cart",
        type: "GET",
        success: function (response) {
            response.forEach(function (item) {
                fillCartItem(item);
            })
        }
    });
}

function renderSizeOption(cartItem) {

    $.ajax({
        url: "/api/sizes",
        type: "GET",
        success: function (response) {
            $('.size-option').empty();
            response.forEach(function (item) {
                let data = {}
                data.sizeId = item.id;
                data.sizeName = item.name;
                data.sizePrice = item.price;
                let classAppendTo = `.size-option-${cartItem.productId}-${cartItem.productId}`;
                $("#size-option-item-cart").tmpl(data).appendTo(classAppendTo);
            })
            $(`.size-option option[value='${cartItem.sizeId}']`).attr('selected', 'selected');
        }
    })
}

function renderCartItem() {

}

function onclickDeleteCartItem() {
    $('.remove-cart-item').on('click', function () {
        confirmWarning("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng?", function () {
            $.ajax()
        })
    })
}

function callAjaxDeleteItem(data) {
    $.ajax({
        method: 'POST',
        url: '/api/cart/delete',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            response.forEach(function (item) {
                fillCartItem(item);
            })
        }, error: function () {
            alertDanger({message: $.i18n('Delete style fail')})
        }
    });
}

function fillCartItem(data) {
    let item = convertToCartItemData(data);
    $("#item-cart-list").tmpl(item).appendTo(".content-item-cart");
    renderSizeOption(item);
}
