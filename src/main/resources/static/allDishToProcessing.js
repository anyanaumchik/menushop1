$(document).ready(
    function () {
        $("#AllDishToProcessing").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "AllDishToProcessing",
                success: function () {
                    showSuccessMessageThis();
                },
                dataType: 'json'
            });
        }
    });

function showSuccessMessageThis() {
    $("#DishAllProcessing").fadeOut();
    $("#DishAllProcessing").fadeIn(1000);
    setTimeout(function () {
        $("#DishAllProcessing").fadeOut(1000);
    }, 3000);
}
