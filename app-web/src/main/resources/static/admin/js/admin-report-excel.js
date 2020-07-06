$(document).ready(function () {
    onclickDownloadExcel();
});

function onclickDownloadExcel() {
    $("#download-report").on('click', function () {
        let fromDate = $("#start-date").val();
        let endDate = $("#end-date").val();
        if (!fromDate) fromDate = ''
        if (!endDate) endDate = '';
        $("#download-report").attr("href", `/admin/api/report/download/revenue.xlsx?fromDate=${fromDate}&endDate=${endDate}`);
        $("#download-report").click();
    })

}