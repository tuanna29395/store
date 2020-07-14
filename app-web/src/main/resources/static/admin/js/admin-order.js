$(document).ready(function () {
    showDetail();
    changeStatus();
});

function showDetail() {
    $('#dataTable').on('click', '.show-detail', function () {
        $('#classModal').modal('show');
        let orderId = $(this).data("id");
        $.ajax({
            type: 'GET',
            url: `/api/order/${orderId}/items`,
            async: false,
            success: function (res) {
                $("#classTable > tbody").empty();
                let stt = 0;
                let totalOriginPrice = 0;
                let totalDiscount = 0;
                let total;
                res.forEach(function (item) {
                    stt += 1;
                    let data = {};
                    data.stt = stt;
                    data.imageUrl = `/images/product/${item.product.imageUrl}`
                    data.productName = item.product.name;
                    data.quantity = item.quantity;
                    data.soldPrice = item.soldPrice;
                    data.sizeName = item.size.name;
                    data.sizePrice = item.sizePrice;
                    data.discount = 0;

                    let percent = parseInt(item.percentDiscount);
                    let soldPrice = parseInt(item.soldPrice);
                    let sizePrice = parseInt(item.sizePrice);

                    totalOriginPrice += (soldPrice + sizePrice) * item.quantity;
                    if (percent) {
                        data.discount = percent;
                        totalDiscount += (soldPrice * percent / 100) * item.quantity;
                    }
                    let html = `<tr>
                            <td>${stt}</td>
                            <td><img class="img-order-item" src="${data.imageUrl}"></td>
                            <td>${data.productName}</td>
                            <td>${data.sizeName}</td>
                            <td>${data.quantity}</td>
                            <td><span class="money" >${data.soldPrice}</span></td>
                 
                            <td>${data.discount}</td>

                        </tr>`
                    $(".order-item-detail").append(html);

                })
                total = totalOriginPrice - totalDiscount;
                $('#total_origin_price').text(totalOriginPrice);
                $('#total_discount').text(totalDiscount);
                $('#total_price').text(total);
            }
        });

    })

}

function changeStatus() {

    $('#dataTable').on('click', '.change-status', function () {

            let status = $(this).data('status');
            let id = $(this).data('id');
            let data = {'status': status};
            $.ajax({
                method: 'POST',
                url: `/api/order/${id}/change-status`,
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function () {
                    location.reload(true);
                }, error: function (response) {
                    alertDanger({message: response.responseText});
                }
            });

    })

}


