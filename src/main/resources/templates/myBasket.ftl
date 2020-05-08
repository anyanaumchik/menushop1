<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Shop</title>
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
    <script type="text/javascript" src="/bookToProcessing.js"></script>
    <script type="text/javascript" src="/allBookToProcessing.js"></script>
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

        #BookAllProcessing {
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
    <#if books?size!=0>
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
                <#list books as book>
                    <tr>
                        <td>#{book.price}</td>
                        <td><#if .lang=="en">
                                ${book.titleEn}
                            <#elseif .lang=="ru">
                                ${book.titleRu}
                            </#if></td>
                        <td>${book.author.surname} ${book.author.name}</td>
                        <td>

                            <button type="submit" id="${book.id}" class="btn btn-primary"
                                    onclick=editCurrentId(${book.id})>
                                ${my_basket_delete}
                            </button>

                        </td>
                        <td>
                            <button type="button" id="qwe${book.id}" class="btn btn-primary"
                                    onclick=editCurrentIdFor(${book.id})>${my_basket_sent_to_processing}
                            </button>
                        </td>
                    </tr>
                    <#assign price += book.price>
                </#list>
                </tbody>
            </table>


        </form>
        <form id="bookToProcessing">
            <button id="bookToProcessingButton" type="submit" hidden="hidden"></button>
        </form>
        <br>
        <a>${my_basket_total_price} ${price}</a>
        <br>
        <form id="AllBookToProcessing">
            <button type="submit"
                    class="btn btn-primary" <#if price==0> disabled="disabled" </#if>>${my_basket_sent_all_to_processing}
            </button>
        </form>
    <#else >${my_basket_cart_is_empty}
    </#if>
    <br>
    <br>
    <input id="bookId" value="${currentId}" type="hidden">
    <input id="bookIdFor" value="${currentIdForProcessing}" type="hidden">
    <#if approvedBooks?size!=0>
        ${my_basket_approved_books}

        <table class="table table-bordered ">
            <thead>
            <tr>
                <th>${my_basket_price}</th>
                <th>${my_basket_title}</th>
                <th>${my_basket_author}</th>

            </tr>
            </thead>
            <tbody>
            <#list approvedBooks as book>
                <tr>
                    <td>#{book.price}</td>
                    <td><#if .lang=="en">
                            ${book.titleEn}
                        <#elseif .lang=="ru">
                            ${book.titleRu}
                        </#if></td>
                    <td>${book.author.surname} ${book.author.name}</td>

                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
    </@f.footer>
    <div id="AddOnProcessing" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
         role="alert">
        <strong>${success}</strong><br> ${my_basket_book_sent_to_processing}
    </div>
    <div id="DeleteFromBasketSuccess" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
         role="alert">
        <strong>${success}</strong><br> ${my_basket_book_deleted_from_basket}
    </div>
    <div id="BookAllProcessing" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
         role="alert">
        <strong>${success}</strong> <br>${my_basket_all_books_sent_to_processing}
    </div>

    <script>
        function editCurrentId(id) {
            document.getElementById("bookId").value = id;
            document.getElementById("qwe" + id).setAttribute("disabled", "disabled");
        }

        function editCurrentIdFor(id) {
            document.getElementById("bookIdFor").value = id;
            document.getElementById(id).setAttribute("disabled", "disabled");
            document.getElementById("bookToProcessingButton").click();
        }
    </script>
</body>
</html>
