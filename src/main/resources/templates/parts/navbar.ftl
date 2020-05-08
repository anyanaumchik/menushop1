<#include "security.ftl">
<#include "*/locale/locale.ftl">
<style>
    .navbar {
        position: fixed;
        z-index: 4;
        right: 0;
        left: 0;
        top: 0;
        padding-bottom: 10px;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Meny.by</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">${navbar_home}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/dish">${navbar_dishes}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cafe">${navbar_cafes}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/category">${navbar_categories}</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">${navbar_users}</a>
                </li>
            </#if>
        </ul>
        <div>
            <form method="get" action="/filter" class="mr-2">
                <input type="text" name="filter" class="form-control"
                       placeholder="${filter_search}" value="${filter!}"/>
            </form>
        </div>
        <div class="mr-2">
            <form id="langEdit">
                <div class="input-group mr-3">
                    <select id="lang" class="custom-select" name="lang" onchange="langEdit()">
                        <option>${navbar_lang}</option>
                        <option value="ru" <#if .lang=="ru"> disabled="disabled"</#if>>${navbar_lang_ru}</option>
                        <option value="en" <#if .lang=="en"> disabled="disabled"</#if>>${navbar_lang_en}</option>
                    </select>
                </div>
                <button id="langSubmit" type="submit" hidden="hidden">ok</button>
            </form>
        </div>

        <#if name=="unknown">
            <a href="/login" class="btn btn-primary">${navbar_log_in}</a>
        <#else >

            <div class="mr-4">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
                    <a href="/account/cart" class="btn btn-primary">${account_basket}</a>
                    <a href="/account" class="btn btn-primary">${name}</a>
                    <button type="submit" class="btn btn-primary">${navbar_log_out}</button>
                </form>
            </div>
        </#if>
    </div>
    <script>
        function langEdit() {
            document.getElementById("langSubmit").click();
        }

        function account() {
            document.getElementById("account123").click();
        }
    </script>
</nav>
<#--<#include "footer.ftl">-->