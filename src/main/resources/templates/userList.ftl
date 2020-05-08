<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
    <table class="table table-bordered center">
        <thead>
        <tr>
            <th>${user_list_username}</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td class="text-center"><a href="/user/${user.id}" class="btn btn-primary">${user_list_edit}</a></td>
                <td class="text-center"><a href="/user/${user.id}/cart" class="btn btn-primary">${user_list_basket}</a>
                </td>
                <td>
                    <#if user.cart.dishesInProcessing?size!=0>
                        <i>
                            ${user_list_have_to_approve}
                        </i>
                    <#--                    <#else >-->
                    <#--                        Don't have dish to approve-->
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>