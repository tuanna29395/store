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
            $("#total-cart").tmpl(item).appendTo(".my-cart");
            response.forEach(function (item) {
                let data = {};
                $("#item-cart").tmpl(item).appendTo(".my-cart");
            })
        }
    })
}