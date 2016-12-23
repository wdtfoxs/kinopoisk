<#include "template.ftl">
<@mainTemplate title="Добавление актера к фильму "/>
<#macro m_body>
<div class="content">
    <div class="register">
        <form action="/add/addActor" method="post">
            <div class="register-top-grid">
                <h3>Add actors to film </h3>
                <div>
                    <#if show != "0">
                        <span>Actors<label>*</label></span>
                        <select class="form-control" name="actor">
                            <#list actors as a>
                                <option value="${a.id}">${a.name} ${a.surname}</option>
                            </#list>
                        </select>
                        <select class="form-control" name="role">
                            <#list roles as r>
                                <option value="${r}">${r}</option>
                            </#list>
                        </select>
                    </#if>
                    <#if actorsForFilm??>
                        Добавленые актеры:
                        <#list actorsForFilm as a>
                            <a href="/actor/${a.id}">${a.name} ${a.surname} as ${a.role}</a>,
                        </#list>
                    </#if>
                </div>
                <div class="clearfix"></div>
            </div>
            <#if show != "0">
                <div class="clearfix"></div>
                <div class="register-but">
                    <input type="submit" value="add">
                </div>
            </#if>

            <div class="clearfix"></div>
        </form>
        <form action="/add/save" method="post">
            <div class="register-but">
                <input type="submit" value="add actors to film" href="/add/save">
            </div>
        </form>
        <div class="clearfix"></div>
    </div>
</div>
</#macro>
