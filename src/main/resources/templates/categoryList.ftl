<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#include "locale/locale.ftl">
<@c.page>

    <#assign index=0>
    <#if isAdmin>
        <div id="accordion">
            <div class="card">
                <div class="card-header" id="headingOne">
                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne"
                            aria-expanded="true" aria-controls="collapseOne">
                        ${categor_list_create}
                    </button>
                </div>
                <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                     data-parent="#accordion">
                    <div class="card-body">
                        <form method="post" action="/category/admin/create">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"
                                          id="basic-addon1">${categor_list_create_title_en}</span>
                                </div>
                                <input type="text" name="titleEn" class="form-control" placeholder="Title En"
                                       aria-label="Title En" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"
                                          id="basic-addon1">${categor_list_create_title_ru}</span>
                                </div>
                                <input type="text" name="titleRu" class="form-control" placeholder="Title Ru"
                                       aria-label="Title Ru" aria-describedby="basic-addon1">
                            </div>

                            <#--                        <input type="text" name="titleEn" placeholder="Title En">-->
                            <#--                        <input type="text" name="titleRu" placeholder="Title Ru">-->
                            <button type="submit" class="btn btn-primary m-2">Create</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </#if>
    <br>
<#--    CATEGORIES-->
    <div class="card-columns">
        <div id="accordion">

            <#list categories as category>



                <div class="card my-3">
                    <div class="card-header" id="heading${category.id}">
                        <a class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse"
                                    data-target="#collapse${category.id}" aria-expanded="true"
                                    aria-controls="collapse${category.id}">
                                <#if .lang=="en">
                                    ${category.titleEn}
                                <#elseif .lang=="ru">
                                    ${category.titleRu}
                                </#if>
                            </button>
                        </a>
                    </div>
                    <div id="collapse${category.id}" class="collapse" aria-labelledby="heading${category.id}"
                         data-parent="#accordion">
                        <div class="card-body">
                            <#if category.getDishes()?size!=0>
                                <#list category.getDishes() as dish>
                                    <#if index<5>
                                        <div class="card">
                                            ${categor_list_author}:<br>
                                            ${categor_list_title}:
                                            <#if .lang=="en">
                                                ${dish.titleEn}
                                            <#elseif .lang=="ru">
                                                ${dish.titleRu}
                                            </#if>
                                            <br>
                                            <a href="/dish/${dish.id}" class="right">${categor_list_dishes_view}</a>
                                        </div>
                                        <#assign index++>
                                    </#if>
                                </#list>
                                <a href="/category/${category.id}" methods="get">${categor_list_all_dishes}</a>
                            <#else>
                                ${categor_list_books_not_found}
                            </#if>
                            <#assign index=0>
                        </div>
                    </div>
                </div>

            </#list>
        </div>
    </div>
</@c.page>