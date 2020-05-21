$(document).ready(function () {

    renderSizeOption();
});

function renderSizeOption() {

    $.ajax({
        url: "/api/sizes",
        type: "GET",
        success: function (response) {
            $('.size-option').empty();
            response.forEach(function (item) {
                let data = {}
                data.sizeId = item.id;
                data.sizeName = item.name;
                data.className = "size-" + item.id;
                data.sizePrice = item.price;
                $("#size-option-item-cart").tmpl(data).appendTo(".size-option");
            })
            let currentSizeId = $(".size-id").val();
            $(`.size-option option[value='${currentSizeId}']`).attr('selected','selected');
        }
    })
}

function setSelectedSize() {

}