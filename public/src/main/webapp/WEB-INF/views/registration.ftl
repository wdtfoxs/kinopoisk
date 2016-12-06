<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "template.ftl">
<@mainTemplate title="Регистрация"/>
<#macro m_body>
<div class="content">
    <div class="register">
        <@form.form commandName="regForm" action="/registration" acceptCharset="UTF-8" method="post">
                <div class="register-top-grid">
                    <h3>Personal Information</h3>
                    <div>
                        <span>Username<label>*</label></span>
                        <@form.input path="username" type="text"/>
                        <@form.errors path="username" cssStyle="color: #d8232a;"/>
                    </div>
                    <div>
                        <span>Email Address<label>*</label></span>
                        <@form.input path="email" type="email"/>
                        <@form.errors path="email" cssStyle="color: #d8232a;" />
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="register-bottom-grid">
                    <h3>Login Information</h3>
                    <div>
                        <span>Password<label>*</label></span>
                        <@form.input path="password" type="password"/>
                        <@form.errors path="password" cssStyle="color: #d8232a;" />
                    </div>
                    <div>
                        <span>Confirm Password<label>*</label></span>
                        <@form.input path="repassword" type="password"/>
                        <@form.errors path="repassword" cssStyle="color: #d8232a;" />
                    </div>
                    <div class="clearfix"></div>
                </div>
            <div class="clearfix"></div>
            <div class="register-but">
                    <input type="submit" value="submit">
            </div>
        <div class="social_reg">
            <a class="btn btn-block btn-social btn-vk" href="/registration/regvk">
                <span class="fa fa-vk"></span> Sign in with VK
            </a>
        </div>
            <div class="social_reg">
                <a class="btn btn-block btn-social btn-vk">
                    <span class="fa fa-vk"></span> Sign in with VK
                </a>
            </div>
            <div class="clearfix"></div>

        </@form.form>
    </div>
</div>
</#macro>
