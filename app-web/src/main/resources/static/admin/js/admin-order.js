$(document).ready(function () {
    changeStatusToSuccess();
    changeStatusToCancel();
    showDetail();
});

function changeStatusOrder(orderId, status) {
    $.ajax({
        type: 'POST',
        url: '/admin/order/change-status',
        dataType: 'json',
        data: {
            _token: $('meta[name="csrf-token"]').attr('content'),
            "orderId": orderId,
            "status": status
        },
        success: function (res) {
            if (res.status == true) {
                $('#btn-success-' + orderId).prop('disabled', true);
                $('#btn-cancel-' + orderId).prop('disabled', true);

                if (status == 2) {
                    $('#order-' + orderId).css("background-color", "lightgreen");
                    $('#status-' + orderId).text('Success');
                } else if (status == 3) {
                    $('#order-' + orderId).css("background-color", "lightgray");
                    $('#status-' + orderId).text('Cancel');
                } else {

                }
            } else {
                Swal.fire({
                    icon: 'warning',
                    title: 'Đã có lỗi xảy ra',
                    showConfirmButton: true,
                })
            }
        }
    });
}

function changeStatusToSuccess() {
    $('.status-success').click(function () {
        if (confirm('Bạn có muốn chuyển trạng thái sang [SUCCESS]')) {
            let orderId = $(this).data("id");
            changeStatusOrder(orderId, 2, this)
        } else {
            return;
        }
    })
}

function changeStatusToCancel() {
    $('.status-cancel').click(function () {
        if (confirm('Bạn có muốn chuyển trạng thái sang [CANCEL]')) {
            let orderId = $(this).data("id");
            changeStatusOrder(orderId, 3)
        } else {
            return;
        }

    })
}

function showDetail() {
    $('.show-detail').click(function () {
        $('#classModal').modal('show');
        let orderId = $(this).data("id");
        $.ajax({
            type: 'GET',
            url: `/api/order/${orderId}/items`,
            async: false,
            success: function (res) {
                $("#classTable > tbody").empty();
                let stt = 0;
                res.forEach(function (item) {
                    stt += 1;
                    let data = {};
                    data.stt = stt;
                    data.imageUrl = `/images/product/${item.product.imageUrl}`
                    data.productName = item.product.name;
                    data.quantity = item.quantity;
                    data.soldPrice = item.soldPrice;
                    data.sizeName = item.size.name;
                    data.sizePrice = item.size.price;
                    data.discount = 0;
                    if (item.product.discount.percent) {
                        data.discount = item.product.discount.percent;
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
                    $('.money');
                })

            }
        });

    })

}


