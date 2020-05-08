$(document).ready(
    function () {
        $("#AllBookToProcessing").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "AllBookToProcessing",
                success: function () {
                    showSuccessMessageThis();
                },
                dataType: 'json'
            });
        }
    });

function showSuccessMessageThis() {
    $("#BookAllProcessing").fadeOut();
    $("#BookAllProcessing").fadeIn(1000);
    setTimeout(function () {
        $("#BookAllProcessing").fadeOut(1000);
    }, 3000);
}
