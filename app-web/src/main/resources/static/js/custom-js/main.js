const PATH_IMAGE = "/images/product/";
$(document).ready(function () {
    renderCartShopping();
});

function renderCartShopping() {
    $.ajax({
        url: "/api/cart",
        type: "GET",
        success: function (response) {
            console.log(response);
            $(".total-cart").remove();
            let item = {};
            let total = response.length;
            if (!total) total = 0;
            item.total = total;
            let totalAmount = 0;
            $("#total-item-cart").tmpl(item).appendTo(".my-cart");
            response.forEach(function (item) {
                let data = {};
                data.imageUrl = PATH_IMAGE + item.product.imageUrl;
                data.productName = item.product.name;
                data.productPrice = item.product.salePrice;
                data.quantity = item.quantity;
                data.amount = item.amount;
                data.sizeName = item.size.name;
                data.sizePrice = item.size.price;
                totalAmount+= item.amount;
                $("#item-cart").tmpl(data).appendTo(".item-cart");
            });
            item.totalAmount = totalAmount;
            $("#total-price-cart").tmpl(item).appendTo(".item-cart");
        }
    })
}