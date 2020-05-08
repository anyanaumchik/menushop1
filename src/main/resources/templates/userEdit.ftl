<#import "parts/common.ftl" as c>
<#include "locale/locale_en.ftl">
<@c.page>
    <form action="/user" method="post">
    <div class="input-group mb-3 mt-2">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">${user_edit_username}</span>
        </div>
        <input type="text" name="username" placeholder="${user_edit_username}"
               class="form-control" aria-label="username"
               aria-describedby="basic-addon1" value="${user.username}">
    </div>
    <#--    <input type="text" name="username" value="${user.username}">-->
    <div class="input-group mb-3 mt-2">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">${user_edit_password}</span>
        </div>
        <input type="text" name="password" placeholder="${user_edit_password}"
               class="form-control" aria-label="password"
               aria-describedby="basic-addon1" value="${user.password}">
    </div>
    <div class="input-group mb-3 mt-2">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">${user_edit_email}</span>
        </div>
        <input type="email" name="email" placeholder="${user_edit_email}"
               class="form-control" aria-label="email"
               aria-describedby="basic-addon1" value="${user.email}">
    </div>
    <#--    <input type="text" name="password" value="${user.password}">-->
    <#--    <input type="email" name="email" value="${user.email}">-->
    <div class="form-group">
        <label for="exampleFormControlSelect1">${user_edit_role}</label>
        <select name="role" class="form-control" id="exampleFormControlSelect1">
            <option value="${user.role.title}">${user.role.title}</option>
            <#list roles as role>
                <#if user.role.title!=role.title>
                    <option value="${role.title}">${role.title}</option>
                </#if>
            </#list>
        </select>
    </div>
    <br>
    <input type="hidden" value="${user.id}" name="userId">
    <button type="submit" class="btn btn-primary">${user_edit_save}</button>
</@c.page>