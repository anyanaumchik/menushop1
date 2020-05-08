<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
    Book Edit
    <#assign index=0>
    <form action="/dish/admin/save" method="post" enctype="multipart/form-data">
        <#if dish.image??>
            <img src="/${dish.image.bookImage}" width="300" height="400">
        </#if>

        <div class="input-group mb-3 mt-2">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${book_price}</span>
            </div>
            <input type="number" step="0.01" name="price" placeholder="${book_price}"
                   class="form-control" aria-label="${book_price}"
                   aria-describedby="basic-addon1" value="${dish.price}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${book_title_ru}</span>
            </div>
            <input type="text" name="titleRu" class="form-control"
                   placeholder="${book_title_ru}" aria-label="${book_title_ru}"
                   aria-describedby="basic-addon1" value="${dish.titleRu}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${book_title_en}</span>
            </div>
            <input type="text" name="titleEn" class="form-control"
                   placeholder="${book_title_en}" aria-label="${book_title_en}"
                   aria-describedby="basic-addon1" value="${dish.titleEn}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${cafe_name}</span>
            </div>
            <input type="text" name="authorName" class="form-control"
                   placeholder="${cafe_name}" aria-label="${cafe_name}"
                   aria-describedby="basic-addon1" value="${dish.cafe.name}">
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">${book_description}</span>
            </div>
            <textarea name="description" class="form-control"
                      aria-label="description">${dish.description}</textarea>
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
                                                ${dish.categories?seq_contains(category)?string("checked", "")}>
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


        <#--        <input type="text" name="titleRu" value="${dish.titleRu}">-->
        <#--        <input type="text" name="titleEn" value="${dish.titleEn}">-->
        <#--        <input type="text" name="authorSurname" value="${dish.cafe.surname}">-->
        <#--        <input type="text" name="authorName" value="${dish.cafe.name}">-->
        <#--        <input type="file" name="image">-->
        <#--        <#if dish.image??>-->
        <#--            <img src="/img/dish/${dish.image.bookImage}">-->
        <#--        </#if>-->

        <#--        <textarea maxlength="1000" rows="10" cols="90"-->
        <#--                  name="description"><#if dish.description??>${dish.description}</#if></textarea>-->
        <#--        <div class="card-columns">-->

        <#--            <#list categories as category>-->
        <#--                <div class="card">-->
        <#--                    <label><input type="checkbox" name="${category}" class="ml-3"-->
        <#--                                ${dish.categories?seq_contains(category)?string("checked", "")}>-->
        <#--                        <#if .lang=="en">-->
        <#--                            ${category.titleEn}-->
        <#--                        <#elseif .lang=="ru">-->
        <#--                            ${category.titleRu}-->
        <#--                        </#if></label>-->
        <#--                </div>-->

        <#--            </#list>-->

        <#--        </div>-->
        <input type="hidden" value="${dish.id}" name="bookId">
        <button type="submit" class="btn btn-primary mt-2">${book_save}</button>
    </form>
    <br>
    <form method="post" action="/dish/admin/delete/${dish.id}">
        <button type="submit" class="btn btn-primary">${book_delete}</button>
    </form>
</@c.page>