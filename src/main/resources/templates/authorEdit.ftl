<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
    ${author_edit_author_edit}
    <form action="/author/admin/{authorId}" method="post" enctype="multipart/form-data">
        <div class="input-group mb-3 mt-2">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${author_edit_author_surname}</span>
            </div>
            <input type="text" name="surname" placeholder="${author_edit_author_surname}"
                   class="form-control" aria-label="${author_edit_author_surname}"
                   aria-describedby="basic-addon1" value="${author.surname}">
        </div>
        <div class="input-group mb-3 mt-2">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">${author_edit_author_name}</span>
            </div>
            <input type="text" name="name" placeholder="${author_edit_author_name}"
                   class="form-control" aria-label="${author_edit_author_name}"
                   aria-describedby="basic-addon1" value="${author.name}">
        </div>
        <#--    <div><label>${author_edit_author_surname}<input type="text" name="surname" value="${author.surname}"></label></div>-->
        <#--    <div><label>${author_edit_author_name}<input type="text" name="name" value="${author.name}"></label></div>-->
        <#--    <div><label><input type="file" name="image"></label></div>-->
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroupFileAddon01">${book_image}</span>
            </div>
            <div class="custom-file">
                <input type="file" name="image" class="custom-file-input" id="inputGroupFile01"
                       aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">${book_choose_file}</label>
            </div>
        </div>
        <#if author.image??>
            <img src="/${author.image.authorImage}" width="500">
        </#if>
        <br>
        <br>
        <input type="hidden" value="${author.id}" name="authorId">
        <button type="submit" class="btn btn-primary">${author_edit_save}</button>
    </form>
    <br>
    <form method="post" action="/author/admin/delete/${author.id}">
        <button type="submit" class="btn btn-primary">${book_delete}</button>
    </form>
</@c.page>