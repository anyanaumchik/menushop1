$(document).ready(
    function () {
        $("#deleteFromBasket").submit(function (event) {
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
                url: "deleteFromBasket",
                success: function () {
                    showSuccessMessage();
                },
                data: JSON.stringify(formData),
                dataType: 'json'
            });
        }
    });

function showSuccessMessage() {
    $("#DeleteFromBasketSuccess").fadeOut();
    $("#DeleteFromBasketSuccess").fadeIn(1000);
    setTimeout(function () {
        $("#DeleteFromBasketSuccess").fadeOut(1000);
    }, 3000);
}
