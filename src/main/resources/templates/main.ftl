<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#include "locale/locale.ftl">

<@c.page>

<#--    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
    <#assign dishIndex=0>
    <#assign cafeIndex=0>
    <div class="mt-3">
        ${main_dish_new}
        <#--        <@spring.message code="filter.dishes"/>-->
    </div>
    <div class="card-deck m-5" id="dish-list">
        <#list dishes as dish>
            <#if dishIndex<4>
                <div class="card">
                    <#assign dishIndex++>
                    <#if dish.image??>
                        <a href="/dish/${dish.id}"><img class="card-img-top"
                                                        src="/${dish.image.dishImage}"
                                                        <#--class="leftimg" width="96" height="125"-->height="370"
                                                        alt="Card image cap"/></a>
                    <#else>
                        <a href="/dish/${dish.id}"> <img class="card-img-top"
                                                         src="/dishImageNotFound.jpg"
                                                         height="348.47" <#--class="leftimg" width="96" height="125"-->
                                                         alt="Card image cap"/></a>
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${dish.titleEn}</h5>
                        <p class="card-text">${dish.price} BYN </p>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
    ${filter_cafe}
    <div class="card-deck m-5">
        <#list cafes as cafe>
            <#if cafeIndex<4>
                <div class="card">
                    <#assign cafeIndex++>
                    <#if cafe.image??>
                        <img src="/${cafe.image.cafeImage}" height="226"/>
                    <#else >
                        <img src="/cafeImageNotFound.png" height="226">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title"> ${cafe.name} </h5>
                        <a href="/cafe/${cafe.id}/dishes" class="card-text">Блюда</a>
                    </div>
                </div>
            </#if>
        </#list>
    </div>

</@c.page>