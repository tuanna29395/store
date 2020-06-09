$( document ).ready(function() {
    $('.select2').select2({
        placeholder: 'Please select one option',
        allowClear: true
    });
    $(".select2 .select2-results").css("max-height","400px");
});

$.ajaxSetup({
    headers: {
        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
    }
});

function confirmDelete() {
    return confirm("Do you want to delete ?")
}

function confirmActive() {
    return confirm("Do you want to active ?")
}

function confirmInactive() {
    return confirm("Do you want to inactive ?")
}

function addItemInArrayCheckbox() {
    var arr_chk = [];
    $(".select-one-item").each(function() {
        if ($(this).is(":checked")) {
            arr_chk.push($(this).attr("id"));
        }
    });

    $('#arr_chk').val(arr_chk);
}

//select item for action
$('#select-all-item').click(function () {
    $('input:checkbox').prop('checked', this.checked);
    addItemInArrayCheckbox()
});

$(".select-one-item").change(function () {
    addItemInArrayCheckbox()
});
