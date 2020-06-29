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
                let data = {};
                data.sizeId = item.id;
                data.sizeName = item.name;

                $("#size-option-item").tmpl(data).appendTo(".size-option");
            })
        }
    })
}
