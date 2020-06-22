$(document).ready(function () {
    $("#dataTable").on('click','.change-status-category', function (event) {
        // event.stopPropagation();
        let tagValue = $(this).find("a");
        let inputValue = $(this).find("input");
        let newStatus = inputValue.data('original') === 2 ? 3 : 2;
        let data = {'status': newStatus};
        let id = $(this).data('id');
        if ($(this).find("a")) {
            $.ajax({
                method: 'POST',
                url: `/api/product/${id}/change-status`,
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function () {
                    if (newStatus === 2) {
                        tagValue.addClass("active");
                    } else {
                        tagValue.removeClass("active");
                    }
                    inputValue.data('original', newStatus);
                }, error: function (response) {
                    alertDanger({message: response.responseText});
                }
            });
        }
    });
});