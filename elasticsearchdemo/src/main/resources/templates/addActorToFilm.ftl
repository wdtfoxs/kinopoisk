<#include "template.ftl">
<@mainTemplate title="Добавление актера к фильму " + ${Session.movie.name}/>
<#macro m_body>
<div class="content">
    <div class="register">
        <form action="/add/actor/add" method="post">
            <div class="register-top-grid">
                <h3>Add actors to film ${Session.movie.name}</h3>
                <div>
                    <span>Actors<label>*</label></span>
                    <select class="form-control" name="actor">
                        <#list actors as a>
                            <option value="${a.id}">${a.name}</option>
                        </#list>
                    </select>
                </div>
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
