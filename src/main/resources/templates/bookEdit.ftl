<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
    Book Edit
    <#assign index=0>
    <form action="/book/admin/save" method="post" enctype="multipart/form-data">
        <#if book.image??>
            <img src="/${book.image.bookImage}" width="300" height="400">
        </#if>

        <div class="input-group mb-3 mt-2">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${book_price}</span>
            </div>
            <input type="number" step="0.01" name="price" placeholder="${book_price}"
                   class="form-control" aria-label="${book_price}"
                   aria-describedby="basic-addon1" value="${book.price}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${book_title_ru}</span>
            </div>
            <input type="text" name="titleRu" class="form-control"
                   placeholder="${book_title_ru}" aria-label="${book_title_ru}"
                   aria-describedby="basic-addon1" value="${book.titleRu}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${book_title_en}</span>
            </div>
            <input type="text" name="titleEn" class="form-control"
                   placeholder="${book_title_en}" aria-label="${book_title_en}"
                   aria-describedby="basic-addon1" value="${book.titleEn}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${author_surname}</span>
            </div>
            <input type="text" name="authorSurname" class="form-control"
                   placeholder="${author_surname}" aria-label="${author_surname}"
                   aria-describedby="basic-addon1" value="${book.author.surname}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${author_name}</span>
            </div>
            <input type="text" name="authorName" class="form-control"
                   placeholder="${author_name}" aria-label="${author_name}"
                   aria-describedby="basic-addon1" value="${book.author.name}">
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">${book_description}</span>
            </div>
            <textarea name="description" class="form-control"
                      aria-label="description">${book.description}</textarea>
        </div>
        <#--                            <input type="number" step="0.01" name="price" placeholder=${book_price}>-->
        <#--                            <input type="text" name="titleRu" placeholder="${book_title_ru}">-->
        <#--                            <input type="text" name="titleEn" placeholder="${book_title_en}">-->
        <#--                            <input type="text" name="authorSurname" placeholder="${author_surname}">-->
        <#--                            <input type="text" name="authorName" placeholder="${author_name}">-->
        <#--                            <textarea class="mt-1" maxlength="1000" rows="10" cols="90"-->
        <#--                                      name="description"></textarea>-->
        <br>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroupFileAddon01">${book_image}</span>
            </div>
            <div class="custom-file">
                <input type="file" name="image" class="custom-file-input" id="inputGroupFile01"
                       aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">${book_choose_file}</label>
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
                                    <label><input type="checkbox" name="${category}" class="ml-3"
                                                ${book.categories?seq_contains(category)?string("checked", "")}>
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
        <#--                    <button type="submit" class="btn btn-primary mt-2">${book_add}</button>-->


        <#--        <input type="text" name="titleRu" value="${book.titleRu}">-->
        <#--        <input type="text" name="titleEn" value="${book.titleEn}">-->
        <#--        <input type="text" name="authorSurname" value="${book.author.surname}">-->
        <#--        <input type="text" name="authorName" value="${book.author.name}">-->
        <#--        <input type="file" name="image">-->
        <#--        <#if book.image??>-->
        <#--            <img src="/img/book/${book.image.bookImage}">-->
        <#--        </#if>-->

        <#--        <textarea maxlength="1000" rows="10" cols="90"-->
        <#--                  name="description"><#if book.description??>${book.description}</#if></textarea>-->
        <#--        <div class="card-columns">-->

        <#--            <#list categories as category>-->
        <#--                <div class="card">-->
        <#--                    <label><input type="checkbox" name="${category}" class="ml-3"-->
        <#--                                ${book.categories?seq_contains(category)?string("checked", "")}>-->
        <#--                        <#if .lang=="en">-->
        <#--                            ${category.titleEn}-->
        <#--                        <#elseif .lang=="ru">-->
        <#--                            ${category.titleRu}-->
        <#--                        </#if></label>-->
        <#--                </div>-->

        <#--            </#list>-->

        <#--        </div>-->
        <input type="hidden" value="${book.id}" name="bookId">
        <button type="submit" class="btn btn-primary mt-2">${book_save}</button>
    </form>
    <br>
    <form method="post" action="/book/admin/delete/${book.id}">
        <button type="submit" class="btn btn-primary">${book_delete}</button>
    </form>
</@c.page>