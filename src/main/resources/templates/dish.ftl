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
        crossorigin = "anonymous"</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/postrequest.js"></script>
    <style>  #addingToCartSuccess {
            position: fixed;
            right: 0;
            top: 50px;
            margin-top: 10px;
            margin-right: 10px;
            display: none;
            z-index: 15;
            text-align: center;
        }</style>
</head>
<body>
<#include "locale/locale.ftl">
<#include "parts/navbar.ftl">
<#include "parts/security.ftl">
<#import "parts/footer.ftl" as f>
<@f.footer>
    <div class="container  mt-5">
    <form id="basketAdd">
        <#if dish.image??>
        <p><img src="/${dish.image.dishImage}" align="left" class="m-2" width="300" height="400"/>
            <#else>
        <p><img src="/dishImageNotFound.jpg" align="left" class="m-2" width="300" height="400"/>
            </#if>
        <div class="mt-5 m-5">
            ${cafe_name}: ${dish.cafe.name}
            <br>
            ${title}:
            <#if .lang=="en">
                ${dish.titleEn}
            <#elseif .lang=="ru">
                ${dish.titleRu}
            </#if>
            <br>
            <br>
            <br>
            <div class="cope_text line-clamp">
                <#if dish.description??>
                    ${dish.description}
                </#if>
            </div>
            <br>
            <b class="mr-2">${dish_price}: ${dish.price} BYN</b>
            <br>
            <button type="submit" class="btn btn-primary" onclick=editCurrentId(${dish.id})
                    <#if name="unknown">disabled="disabled"</#if>>
                ${basket_add}
            </button>

            <#if isAdmin>
                <a href="/dish/admin/${dish.id}"
                   class="btn btn-primary ml-2 leftText">${edit}</a>
            </#if>

            <input type="hidden" id="dishId" value="${dish.id}">
    </form>

    </div></@f.footer>
<div id="addingToCartSuccess" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
     role="alert">
    <strong>${success}</strong> ${basket_add_alert}
</body>
</html>