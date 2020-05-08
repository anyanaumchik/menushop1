$(document).ready(
    function () {
        $("#approvedBook").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            var book = {
                id: $("#bookId").val()
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "approvedBook",
                success: function () {
                    showSuccessMessage1();
                },
                data: JSON.stringify(book),
                dataType: 'json'
            });
        }
    });

function showSuccessMessage1() {
    $("#AddOnProcessing").fadeOut();
    $("#AddOnProcessing").fadeIn(1000);
    setTimeout(function () {
        $("#AddOnProcessing").fadeOut(1000);
    }, 3000);
}
