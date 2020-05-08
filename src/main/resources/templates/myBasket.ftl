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
    <script type="text/javascript" src="/deleteFromBasket.js"></script>
    <script type="text/javascript" src="/dishToProcessing.js"></script>
    <script type="text/javascript" src="/allDishToProcessing.js"></script>
    <style>
        #DeleteFromBasketSuccess {
            position: fixed;
            right: 0;
            top: 50px;
            margin-top: 10px;
            margin-right: 10px;
            display: none;
            z-index: 15;
            text-align: center;
        }

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

        #DishAllProcessing {
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
<#global currentId=0>
<#global currentIdForProcessing=0>
<#include "locale/locale.ftl">
<#import "parts/footer.ftl" as f>
<@f.footer>
<div class="container center mt-5">
    <#if dishes?size!=0>
        <form id="deleteFromBasket">

            <table class="table table-bordered ">
                <thead>
                <tr>
                    <th>${my_basket_price}</th>
                    <th>${my_basket_title}</th>
                    <th>${my_basket_author}</th>
                    <th></th>
                    <th></th>
                </tr>
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
                        <td>

                            <button type="submit" id="${dish.id}" class="btn btn-primary"
                                    onclick=editCurrentId(${dish.id})>
                                ${my_basket_delete}
                            </button>

                        </td>
                        <td>
                            <button type="button" id="qwe${dish.id}" class="btn btn-primary"
                                    onclick=editCurrentIdFor(${dish.id})>${my_basket_sent_to_processing}
                            </button>
                        </td>
                    </tr>
                    <#assign price += dish.price>
                </#list>
                </tbody>
            </table>


        </form>
        <form id="dishToProcessing">
            <button id="dishToProcessingButton" type="submit" hidden="hidden"></button>
        </form>
        <br>
        <a>${my_basket_total_price} ${price}</a>
        <br>
        <form id="AlldishToProcessing">
            <button type="submit"
                    class="btn btn-primary" <#if price==0> disabled="disabled" </#if>>${my_basket_sent_all_to_processing}
            </button>
        </form>
    <#else >${my_basket_cart_is_empty}
    </#if>
    <br>
    <br>
    <input id="dishId" value="${currentId}" type="hidden">
    <input id="dishIdFor" value="${currentIdForProcessing}" type="hidden">
    <#if approvedDishes?size!=0>
        ${my_basket_approved}

        <table class="table table-bordered ">
            <thead>
            <tr>
                <th>${my_basket_price}</th>
                <th>${my_basket_title}</th>
                <th>${my_basket_author}</th>

            </tr>
            </thead>
            <tbody>
            <#list approvedDishes as dish>
                <tr>
                    <td>#{dish.price}</td>
                    <td><#if .lang=="en">
                            ${dish.titleEn}
                        <#elseif .lang=="ru">
                            ${dish.titleRu}
                        </#if></td>
                    <td> ${dish.cafe.name}</td>

                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
    </@f.footer>
    <div id="AddOnProcessing" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
         role="alert">
        <strong>${success}</strong><br> ${my_basket_sent_to_processing}
    </div>
    <div id="DeleteFromBasketSuccess" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
         role="alert">
        <strong>${success}</strong><br> ${my_basket_deleted_from_basket}
    </div>
    <div id="DishAllProcessing" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
         role="alert">
        <strong>${success}</strong> <br>${my_basket_all_sent_to_processing}
    </div>

    <script>
        function editCurrentId(id) {
            document.getElementById("dishId").value = id;
            document.getElementById("qwe" + id).setAttribute("disabled", "disabled");
        }

        function editCurrentIdFor(id) {
            document.getElementById("dishIdFor").value = id;
            document.getElementById(id).setAttribute("disabled", "disabled");
            document.getElementById("dishToProcessingButton").click();
        }
    </script>
</body>
</html>
