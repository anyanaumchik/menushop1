<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
<#--    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
    <a href="/account/cart">${account_basket}</a>
    <br><br>
    <a href="/account/edit">${account_configuration}</a>
</@c.page>