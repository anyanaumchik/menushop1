<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">

<@c.page>

<#--    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
    <form action="/registration" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${registration_username}:</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')} ${(usernameExistsError??)?string('is-invalid', '')}"
                       placeholder=${registration_username}/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${registration_create_username_error}
                    </div>
                </#if>
                <#if usernameExistsError??>
                    <div class="invalid-feedback">
                        ${registration_create_username_exist_error}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${registration_password}:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}${(passwordDifferentError??)?string('is-invalid', '')}"
                       placeholder="${registration_password}"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${registration_create_new_parssword_error}
                    </div>
                </#if>
                <#if passwordDifferentError??>
                    <div class="invalid-feedback">
                        ${registration_create_new_parssword_different_error}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${registration_password_repeat}:</label>
            <div class="col-sm-6">
                <input type="password" name="password1"
                       class="form-control ${(password1Error??)?string('is-invalid', '')}${(password1EmptyError??)?string('is-invalid', '')}"
                       placeholder="${registration_password_repeat}"/>
                <#if password1Error??>
                    <div class="invalid-feedback">
                        ${registration_create_new_parssword_different_error}
                    </div>
                </#if><#if password1EmptyError??>
                    <div class="invalid-feedback">
                        ${registration_create_new_repeat_parssword_error}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${registration_email}:</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')}${(emailExistsError??)?string('is-invalid', '')}"
                       placeholder="some@some.com"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${registration_create_email_error}
                    </div>
                </#if>
                <#if emailExistsError??>
                    <div class="invalid-feedback">
                        ${registration_create_email_exist_error}
                    </div>
                </#if>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">${registration_create}</button>
    </form>
</@c.page>