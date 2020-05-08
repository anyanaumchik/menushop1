<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dishes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="http://code.jquery.com/jquery-1.11.0.min.js">
        integrity = "sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin = "anonymous" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/approvedDish.js"></script>
    <style>
        #AddOnProcessing {
            position: fixed;
            right: 0;
            top: 50px;
            margin-top: 10px;
            margin-right: 10px;
            display: none;
            z-index: 15;
            text-align: center;
        }
    </style>
</head>
<body>
<#include "parts/navbar.ftl">
<#assign price = 0>
<#global dishId=0>
<#assign processingPrice = 0>
<#import "parts/footer.ftl" as f>
<#include "locale/locale.ftl">
<@f.footer>
    <div class="container center mt-5">
        ${admin_user_basket_dish_in_cart}

        <form id="approvedDish">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>${admin_user_basket_price}</th>
                    <th>${admin_user_basket_title}</th>
                    <th>${admin_user_basket_author}</th>
                </thead>
                <tbody>
                <#list dishes as dish>
                    <tr>
                        <td>#{dish.price}</td>
                        <td><#if .lang=="en">
                                ${dish.titleEn}
                            <#elseif .lang=="ru">
                                ${dish.titleRu}
                            </#if></td>
                        <td> ${dish.cafe.name}</td>

                    </tr>
                    <#assign price += dish.price>
                </#list>
                </tbody>
            </table>
            <br>
            <a>${admin_user_basket_total_price} ${price}</a>
            <br>
            <br>
            ${admin_user_basket_dish_in_processing}
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>${admin_user_basket_price}</th>
                    <th>${admin_user_basket_title}</th>
                    <th>${admin_user_basket_author}</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list dishesInProcessing as dishInProcessing>
                    <tr>
                        <td>#{dishInProcessing.price}</td>
                        <td><#if .lang=="en">
                                ${dishInProcessing.titleEn}
                            <#elseif .lang=="ru">
                                ${dishInProcessing.titleRu}
                            </#if></td>
                        <td> ${dishInProcessing.cafe.name}</td>
                        <td>
                            <button type="submit" id="${dishInProcessing.id}" class="btn btn-primary"
                                    onclick=editCurrentId(${dishInProcessing.id})>
                                ${admin_user_basket_approved}
                            </button>
                        </td>
                    </tr>
                    <#assign processingPrice += dishInProcessing.price>
                </#list>
                </tbody>
            </table>
        </form>
        <br>
        <a> ${admin_user_basket_price} ${processingPrice}</a>
    </div>
</@f.footer>
<input type="hidden" id="userId" value="${user.id}">
<input type="hidden" id="dishId" value="${dishId}">
<div id="AddOnProcessing" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
     role="alert">
    <strong>${success}</strong><br>${admin_user_basket_dish_in_processing}
</div>
<script>
    function editCurrentId(id) {
        document.getElementById("dishId").value = id;
    }
</script>
</body>
</html>
