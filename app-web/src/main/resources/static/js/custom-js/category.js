$(document).ready(function () {
    renderCategories();
});

function renderCategories() {
    $.ajax({
        url: "/api/categories",
        type: "GET",
        success: function (response) {
            response.forEach(function (item) {
                $("#item-category").tmpl(item).appendTo("#content-categories");
            })
        }
    })
}