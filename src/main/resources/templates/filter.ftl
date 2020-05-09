<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#include "locale/locale.ftl">

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
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
    }
</style>

<@c.page>

    <#assign index=0>
<#--    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
    <#assign currentId=0>
    ${filter}
<#--    <@spring.message code="filter.dishes"/>-->
    <#if dishes?? && dishes?size!=0>
        <div class="ml-5 mr-5">
            <form id="basketAdd">
                <div class="card-columns">
                    <#list dishes as dish>
                        <div class="card my-3">

                            <#if dish.image??>
                            <p><img src="/${dish.image.dishImage}" class="leftimg" width="96" height="125">
                                <#else>
                            <p><img src="/dishImageNotFound.jpg" class="leftimg" width="96" height="125">
                                </#if>
                                ${cafe_name}: ${dish.cafe.name}
                                <br>
                                ${title}
                                <#if .lang=="en">
                                    ${dish.titleEn}
                                <#elseif .lang=="ru">
                                    ${dish.titleRu}
                                </#if>
                            <div class="cope_text line-clamp">
                                <#if dish.description??>
                                    ${dish.description}
                                </#if>
                                <br>
                                <br>
                                <br>
                            </div>
                            </p>
                            <div class="card-footer text-muted text-right">
                                <a href="/dish/${dish.id}"
                                   class="btn btn-primary ml-2 leftText">${view}</a>
                                <#if isAdmin>
                                    <a href="/dish/admin/${dish.id}"
                                       class="btn btn-primary ml-2 leftText">${edit}</a>
                                </#if>
                                <br>
                                <b class="mr-2">${dish_price}: ${dish.price} BYN</b>
                                <#if name!="unknown">
                                </#if>
                            </div>
                        </div>
                    </#list>
                </div>
                <input type="hidden" id="dishId" value="${currentId}">
            </form>
        </div>
    <#else>
        <h5>${filter_not}</h5>
    </#if>
    ${filter_cafe_not}
    <#if cafes?? && cafes?size!=0>
        <div class="card-columns">
            <#list cafes as cafe>

                <div class="card m-2" <#--style="width: 20rem;"-->>
                    <#if cafe.image??>
                        <img class="card-img-top" src="/i${cafe.image.cafeImage}" height="400">
                    </#if>
                    <div class="card-body">
                        <a class="card-titlem-2"> ${cafe.name}</a>
                        <br>
                        <div class="card-text">
                            <a href="/cafe/${cafe.id}/dishes" class="btn btn-primary m-2">список книг</a>
                            <br>
                            <#if isAdmin>
                                <a href="/cafe/admin/${cafe.id}" class="btn btn-primary m-2">edit</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <h5>${filter_cafe_not}</h5>
    </#if>
    ${filter_categories}
    <#if category??>

        <div class="card-columns">
            <div id="accordion">
                <#assign dishes = category.getDishes()>
                <div class="card my-3">
                    <div class="card-header" id="heading${category.id}">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse"
                                    data-target="#collapse${category.id}" aria-expanded="true"
                                    aria-controls="collapse${category.id}">
                                <#if .lang=="en">
                                    ${category.titleEn}
                                <#elseif .lang=="ru">
                                    ${category.titleRu}
                                </#if>
                            </button>
                        </h5>
                    </div>
                    <div id="collapse${category.id}" class="collapse" aria-labelledby="heading${category.id}"
                         data-parent="#accordion">
                        <div class="card-body">
                            <#list dishes as dish>
                                <#if index<5>
                                    <div class="card">
                                        Cafe: ${dish.cafe.name} <br>
                                        Title:
                                        <#if .lang=="en">
                                            ${dish.titleEn}
                                        <#elseif .lang=="ru">
                                            ${dish.titleRu}
                                        </#if>
                                        <br>
                                        <a href="/dish/${dish.id}" class="right">Dish</a>
                                    </div>
                                    <#assign index++>
                                </#if>
                            </#list>
                            <#assign index=0>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        <h5>${filter_categories_not}</h5>
    </#if>

</@c.page>