<#import "parts/common.ftl" as c>
<@c.page>
    <#include "locale/locale.ftl">
    <form action="/account/edit" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if currentUser??>${currentUser.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}
                        ${(usernameExistError??)?string('is-invalid', '')}"
                       placeholder="User name"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${account_configuration_username_error}
                    </div>
                </#if>
                <#if usernameExistError??>
                    <div class="invalid-feedback">
                        ${account_configuration_username_exist_error}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Old password:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}
                         ${(passwordInputError??)?string('is-invalid', '')}"
                       placeholder="Old Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${account_configuration_old_parssword_error}
                    </div>
                <#--                </#if>-->
                <#elseif passwordInputError??>
                    <div class="invalid-feedback">
                        ${account_configuration_old_parssword_input_error}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">New password:</label>
            <div class="col-sm-6">
                <input type="password" name="newPassword"
                       class="form-control
                        ${(password1EmptyError??)?string('is-invalid', '')}
                        ${(password1DifferentError??)?string('is-invalid', '')}"
                       placeholder="New Password"/>
                <#if password1EmptyError??>
                    <div class="invalid-feedback">
                        ${account_configuration_new_parssword_error}
                    </div>
                </#if> <#if password1DifferentError??>
                    <div class="invalid-feedback">
                        ${account_configuration_new_parssword_different_error}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Repeat password:</label>
            <div class="col-sm-6">
                <input type="password" name="repeatNewPassword"
                       class="form-control
                        ${(password2EmptyError??)?string('is-invalid', '')}
                        ${(password2DifferentError??)?string('is-invalid', '')}"
                       placeholder="Repeat password"/>
                <#if password2EmptyError??>
                    <div class="invalid-feedback">
                        ${account_configuration_new_repeat_parssword_error}
                    </div>
                <#elseif password2DifferentError??>
                    <div class="invalid-feedback">
                        ${account_configuration_new_parssword_different_error}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="<#if currentUser??>${currentUser.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')}
                        ${(emailExistError??)?string('is-invalid', '')}"
                       placeholder="some@some.com"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${account_configuration_email_error}
                    </div>
                </#if><#if emailExistError??>
                    <div class="invalid-feedback">
                        ${account_configuration_email_exist_error}
                    </div>
                </#if>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Edit</button>
    </form>

</@c.page>