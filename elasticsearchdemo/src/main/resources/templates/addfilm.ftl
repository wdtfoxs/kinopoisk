<#include "template.ftl">
<@mainTemplate title="Добавление фильма"/>
<#macro m_body>
<div class="content">
    <div class="register">
        <form action="/add/film" method="post">
            <div class="register-top-grid">
                <h3>Add film</h3>
                <div>
                    <span>Name of film<label>*</label></span>
                    <input name="name" type="text"/>
                </div>
                <div>
                    <span>Description<label>*</label></span>
                    <input name="description" type="text"/>
                </div>
                <div>
                    <span>Year<label>*</label></span>
                    <input name="year" type="number"/>
                </div>
                <div>
                    <span>Age<label>*</label></span>
                    <input name="age" type="number"/>
                </div>
                <div>
                    <span>Link on image<label>*</label></span>
                    <input name="image" type="text"/>
                </div>
                <div>
                    <span>Link on YouTube trailer<label>*</label></span>
                    <input name="trailer" type="text"/>
                </div>
                <div>
                    <span>Country<label>*</label></span>
                    <select class="form-control" name="country">
                        <#list country as c>
                            <option value="${c.code}">${c.name}</option>
                        </#list>
                    </select>
                </div>
                <#--<div>-->
                    <#--<span>Actors<label>*</label></span>-->
                    <#--<select class="form-control" name="country">-->
                        <#--<#list actors as a>-->
                            <#--<option value="${a.id}">${a.name}</option>-->
                        <#--</#list>-->
                    <#--</select>-->
                <#--</div>-->
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
            <div class="register-but">
                <input type="submit" value="submit">
            </div>
            <div class="clearfix"></div>
        </form>
    </div>
</div>
</#macro>
