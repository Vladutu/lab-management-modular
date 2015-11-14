function onWinLoad() {
    window.onload = function () {
        document.getElementById('password').style.display = 'none';
        document.getElementById('passError').style.display = 'none';
    }
};

function onPasswordButtonClick() {
    $(document).ready(function () {
        $("#passButton").hide();
        $("#password").show();
        $("#passError").show();
        $("#passLabel").text("New password:");
    });
}