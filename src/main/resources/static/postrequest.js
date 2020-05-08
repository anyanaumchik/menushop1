$(document).ready(
    function () {
        $("#basketAdd").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            var formData = {
                id: $("#dishId").val()
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/saveDish",
                success: function () {
                    showSuccessMessage();
                },

                data: JSON.stringify(formData),

                dataType: 'json'
            });
        }
    });

function showSuccessMessage() {
    $("#addingToCartSuccess").fadeOut();
    $("#addingToCartSuccess").fadeIn(1000);
    setTimeout(function () {
        $("#addingToCartSuccess").fadeOut(1000);
    }, 3000);
}
