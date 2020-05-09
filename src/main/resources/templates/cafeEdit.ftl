<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
    ${author_edit_author_edit}
    <form action="/cafe/admin/{cafeId}" method="post" enctype="multipart/form-data">
        <div class="input-group mb-3 mt-2">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${author_edit_author_name}</span>
            </div>
            <input type="text" name="name" placeholder="${author_edit_author_name}"
                   class="form-control" aria-label="${author_edit_author_name}"
                   aria-describedby="basic-addon1" value="${cafe.name}">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroupFileAddon01">${image}</span>
            </div>
            <div class="custom-file">
                <input type="file" name="image" class="custom-file-input" id="inputGroupFile01"
                       aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">${choose_file}</label>
            </div>
        </div>
        <#if cafe.image??>
            <img src="/${cafe.image.cafeImage}" width="500">
        </#if>
        <br>
        <br>
        <input type="hidden" value="${cafe.id}" name="cafeId">
        <button type="submit" class="btn btn-primary">${author_edit_save}</button>
    </form>
    <br>
    <form method="post" action="/cafe/admin/delete/${cafe.id}">
        <button type="submit" class="btn btn-primary">${delete}</button>
    </form>
</@c.page>