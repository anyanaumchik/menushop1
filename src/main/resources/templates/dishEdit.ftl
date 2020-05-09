<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
    Dish Edit
    <#assign index=0>
    <form action="/dish/admin/save" method="post" enctype="multipart/form-data">
        <#if dish.image??>
            <img src="/${dish.image.dishImage}" width="300" height="400">
        </#if>

        <div class="input-group mb-3 mt-2">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${dish_price}</span>
            </div>
            <input type="number" step="0.01" name="price" placeholder="${dish_price}"
                   class="form-control" aria-label="${dish_price}"
                   aria-describedby="basic-addon1" value="${dish.price}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${title_ru}</span>
            </div>
            <input type="text" name="titleRu" class="form-control"
                   placeholder="${title_ru}" aria-label="${title_ru}"
                   aria-describedby="basic-addon1" value="${dish.titleRu}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${title_en}</span>
            </div>
            <input type="text" name="titleEn" class="form-control"
                   placeholder="${title_en}" aria-label="${title_en}"
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
                <span class="input-group-text">${description}</span>
            </div>
            <textarea name="description" class="form-control"
                      aria-label="description">${dish.description}</textarea>
        </div>

        <br>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroupFileAddon01">${image}</span>
            </div>
            <div class="custom-file">
                <input type="file" name="image" class="custom-file-input" id="inputGroupFile01"
                       aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">${choose_file}</label>
            </div>
        </div>
        <#--                            <input type="file" name="image">-->
        <div id="accordionTwo">
            <div class="card">
                <div class="card-header" id="headingTwo">
                    <button class="btn btn-link" type="button" data-toggle="collapse"
                            data-target="#collapseTwo"
                            aria-expanded="true" aria-controls="collapseOne">
                        ${add_category}
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

        <input type="hidden" value="${dish.id}" name="dishId">
        <button type="submit" class="btn btn-primary mt-2">${save}</button>
    </form>
    <br>
    <form method="post" action="/dish/admin/delete/${dish.id}">
        <button type="submit" class="btn btn-primary">${delete}</button>
    </form>
</@c.page>