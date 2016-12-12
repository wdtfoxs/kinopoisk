<#include "template.ftl">
<@mainTemplate title="Регистрация"/>
<#macro m_body>
<div class="content">
    <div class="register">
        <form action="/reg" method="post">
                <div class="register-top-grid">
                    <h3>Personal Information</h3>
                    <div>
                        <span>Username<label>*</label></span>
                        <input name="username" type="text"/>
                        <#if username??>
                            <h6>${username}</h6>
                        <#elseif usernameex??>
                            <h6>${usernameex}</h6>
                        </#if>
                    </div>
                    <div>
                        <span>Email Address<label>*</label></span>
                        <input name="email" type="email"/>
                        <#if email??>
                            <h6>${email}</h6>
                        <#elseif emailex??>
                            <h6>${emailex}</h6>
                        </#if>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="register-bottom-grid">
                    <h3>Login Information</h3>
                    <div>
                        <span>Password<label>*</label></span>
                        <input name="password"  type="password"/>
                        <#if password??>
                            <h6>${password}</h6>
                        </#if>
                    </div>
                    <div>
                        <span>Confirm Password<label>*</label></span>
                        <input name="repassword" type="password"/>
                        <#if repassword??>
                            <h6>${repassword}</h6>
                        </#if>
                    </div>
                    <div class="clearfix"></div>
                </div>
            <div class="clearfix"></div>
            <div class="register-but">
                <input type="submit" value="submit">
            </div>
            <div class="social_reg">
                <a class="btn btn-block btn-social btn-vk" href="/reg/regvk">
                    <span class="fa fa-vk"></span> Sign in with VK
                </a>
            </div>
            <div class="social_reg">
                <a class="btn btn-block btn-social btn-vk">
                    <span class="fa fa-vk"></span> Sign in with VK
                </a>
            </div>
            <div class="clearfix"></div>
        </form>
    </div>
</div>
</#macro>
