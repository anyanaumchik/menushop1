<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Book Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>

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
    <style>
        #addingToCartSuccess {
            position: fixed;
            right: 0;
            top: 50px;
            margin-top: 10px;
            margin-right: 10px;
            display: none;
            z-index: 15;
            text-align: center;
        }

        #addingToCartError {
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
    <style>
        .leftimg {
            float: left; /* Выравнивание по левому краю */
            margin: 7px 7px 7px 7px; /* Отступы вокруг картинки */
        }

        .leftText {
            float: left;
        }

    </style>
    <style>
        .cope_text {
            overflow: hidden;
            line-height: 20px;
        }

        .line-clamp {
            display: -webkit-box;
            padding-right: 20px;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
        }
    </style>

    <style>.card-columns {

            column-count: 3;
        }

    </style>

</head>
<body>
<#include "parts/security.ftl">
<#include "parts/navbar.ftl">
<#include "locale/locale.ftl">
<#import "parts/pager.ftl" as p>
<#import "parts/footer.ftl" as f>


<#--<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
<@f.footer>
    <div class="container mt-3">

        <#assign sum=0>
        <#assign currentId=0>
        <h5 align="center"> <#if categoryPage??>
            ${book_page_category}
            <#--        <@spring.message code="book.page.category"/>-->
            <#if .lang=="en">
                ${category.titleEn}
            <#elseif .lang=="ru">
                ${category.titleRu}
            </#if>
            <#elseif authorPage??>
                ${book_page_author}
            <#--        <@spring.message code="book.page.cafe"/>-->
                ${cafe.name}
            <#else >

        </h5>
        <#if isAdmin>
            <#if bookAddSuccess??>
                ${book_book_add_success}
            </#if>
            <#if priceError??>
                ${book_price_error}
            </#if>
            <#if authorNotFoundError??>
                ${book_error_author_not_Found}
            </#if>
            <div id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne"
                                aria-expanded="true" aria-controls="collapseOne">
                            ${book_add_book}
                        </button>
                    </div>
                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                         data-parent="#accordion">
                        <div class="card-body">
                            <form method="post" action="/book<#--/admin/create-->" enctype="multipart/form-data">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">${book_price}</span>
                                    </div>
                                    <input type="number" step="0.01" name="price" placeholder="${book_price}"
                                           class="form-control" aria-label="${book_price}"
                                           aria-describedby="basic-addon1"/>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">${book_title_ru}</span>
                                    </div>
                                    <input type="text" name="titleRu" class="form-control"
                                           placeholder="${book_title_ru}" aria-label="${book_title_ru}"
                                           aria-describedby="basic-addon1"/>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">${book_title_en}</span>
                                    </div>
                                    <input type="text" name="titleEn" class="form-control"
                                           placeholder="${book_title_en}" aria-label="${book_title_en}"
                                           aria-describedby="basic-addon1"/>
                                </div>

                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">${cafe_name}</span>
                                    </div>
                                    <input type="text" name="authorName" class="form-control"
                                           placeholder="${cafe_name}" aria-label="${cafe_name}"
                                           aria-describedby="basic-addon1"/>
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">${book_description}</span>
                                    </div>
                                    <textarea name="description" class="form-control"
                                              aria-label="description"></textarea>
                                </div>
                                <#--                            <input type="number" step="0.01" name="price" placeholder=${book_price}>-->
                                <#--                            <input type="text" name="titleRu" placeholder="${book_title_ru}">-->
                                <#--                            <input type="text" name="titleEn" placeholder="${book_title_en}">-->
                                <#--                            <input type="text" name="authorSurname" placeholder="${author_surname}">-->
                                <#--                            <input type="text" name="authorName" placeholder="${author_name}">-->
                                <#--                            <textarea class="mt-1" maxlength="1000" rows="10" cols="90"-->
                                <#--                                      name="description"></textarea>-->
                                <br/>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupFileAddon01">${book_image}</span>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" name="image" class="custom-file-input" id="inputGroupFile01"
                                               aria-describedby="inputGroupFileAddon01"/>
                                        <label class="custom-file-label"
                                               for="inputGroupFile01">${book_choose_file}</label>
                                    </div>
                                </div>
                                <#--                            <input type="file" name="image">-->
                                <div id="accordionTwo">
                                    <div class="card">
                                        <div class="card-header" id="headingTwo">
                                            <button class="btn btn-link" type="button" data-toggle="collapse"
                                                    data-target="#collapseTwo"
                                                    aria-expanded="true" aria-controls="collapseOne">
                                                ${book_add_category}
                                            </button>
                                        </div>
                                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
                                             data-parent="#accordionTwo">
                                            <div class="card-body">
                                                <div class="card-columns">
                                                    <#list categories as category>
                                                        <div class="card">
                                                            <label><input type="checkbox" name="${category}"
                                                                          class="ml-3"/>
                                                                <#if .lang=="en">
                                                                    ${category.titleEn}
                                                                <#elseif .lang=="ru">
                                                                    ${category.titleRu}
                                                                </#if></label>

                                                        </div>

                                                    </#list>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary mt-2">${book_add}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    <#--        <div>${book_list}</div>-->
        </#if>
    </div>
    <#if page.numberOfElements!=0>
        <div class="ml-5 mr-5" style="padding-right: 50px;padding-left: 50px">

            <form id="basketAdd">
                <@p.pager url page />
                <div class="card-columns" id="book-list">

                    <#list page.content as book>

                        <div class="card my-3" data-id="${book.id}">


                            <#if book.image??>
                            <p><a href="/book/${book.id}"><img src="/${book.image.bookImage}" class="leftimg"
                                                               width="96" height="125"/></a>
                                <#else>
                            <p><a href="/book/${book.id}"><img src="/bookImageNotFound.jpg" class="leftimg"
                                                               width="96" height="125"/></a>
                                </#if>
                                <#--${book_author}:--> <i>${book.cafe.name} </i>
                                <br/>
                                <#--                                ${book_title}:-->

                                <b><#if .lang=="en">
                                        ${book.titleEn}
                                        <#if book.titleEn?length<32><br><br></#if>
                                    <#elseif .lang=="ru">
                                        ${book.titleRu}
                                        <#if book.titleRu?length<32><br><br></#if>
                                    </#if></b>
                            <div class="cope_text line-clamp">
                                <#if book.description??>
                                    ${book.description}
                                    <#if book.description?length<52>
                                        <br/>
                                        <br/>
                                        <br/>
                                    <#elseif  book.description?length<104>
                                        <br/>
                                        <br/>
                                    </#if>
                                </#if>
                            </div>
                            </p>
                            <div class="card-footer text-muted text-right">
                                <a href="/book/${book.id}"
                                   class="btn btn-primary ml-2 leftText">${book_view}</a>
                                <#--                                <#if isAdmin>-->
                                <#--                                    <a href="/book/admin/${book.id}"-->
                                <#--                                       class="btn btn-primary ml-2 leftText">${book_edit}</a>-->
                                <#--                                </#if>-->

                                <b class="mr-2">${book_price}: ${book.price} BYN</b>
                                <br/>

                                <button type="submit" class="btn btn-primary" onclick="editCurrentId(${book.id})"
                                        <#if name="unknown">disabled="disabled"</#if>>
                                    ${book_basket_add}
                                </button>


                            </div>
                        </div>
                    </#list>

                </div> <@p.pager url page />
                <input type="hidden" id="bookId" value="${currentId}"/>
            </form>
        </div>
    <#else>
        <div class="container">
            <br/>
            <h5 align="center">Books not found</h5>
        </div>
    </#if>

</@f.footer>
<div id="addingToCartSuccess" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
     role="alert">
    <strong>Success</strong><br> ${book_basket_add_alert}</div>

<script>
    function editCurrentId(id) {
        document.getElementById("bookId").value = id;
    }
</script>
</body>
</html>