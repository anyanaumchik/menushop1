<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#include "locale/locale.ftl">

<@c.page>

<#--    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
    <#assign bookIndex=0>
    <#assign authorIndex=0>
    <div class="mt-3">
        ${main_book_new}
        <#--        <@spring.message code="filter.books"/>-->
    </div>
    <div class="card-deck m-5" id="book-list">
        <#list books as book>
            <#if bookIndex<4>
                <div class="card">
                    <#assign bookIndex++>
                    <#if book.image??>
                        <a href="/book/${book.id}"><img class="card-img-top"
                                                        src="/${book.image.bookImage}"
                                                        <#--class="leftimg" width="96" height="125"-->height="370"
                                                        alt="Card image cap"/></a>
                    <#else>
                        <a href="/book/${book.id}"> <img class="card-img-top"
                                                         src="/bookImageNotFound.jpg"
                                                         height="348.47" <#--class="leftimg" width="96" height="125"-->
                                                         alt="Card image cap"/></a>
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${book.titleEn}</h5>
                        <p class="card-text">${book.price} BYN </p>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
    ${filter_authors}
    <div class="card-deck m-5">
        <#list authors as author>
            <#if authorIndex<4>
                <div class="card">
                    <#assign authorIndex++>
                    <#if author.image??>
                        <img src="/${author.image.authorImage}" height="226"/>
                    <#else >
                        <img src="/authorImageNotFound.png" height="226">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${author.surname} ${author.name} </h5>
                        <a href="/author/${author.id}/books" class="card-text">Книги</a>
                    </div>
                </div>
            </#if>
        </#list>
    </div>

</@c.page>