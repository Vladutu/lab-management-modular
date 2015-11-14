$(document).ready(function (e) {

    $("input[id=pnc]").keyup(function () {

        var pnc = $(this).val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            contentType: "application/json",
            method: "POST",
            url: "localhost:8080/admin/users/new/ajax",
            dataType: "json",
            data: JSON.stringify(pnc),
            success: function (data) {
                $("#firstName").val(data.firstName);
                $("#lastName").val(data.lastName);
                $("#email").val(data.email);
                $("#userType").val(data.userType);
            }
        });

    });

});