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
                    </div>
                    <div>
                        <span>Email Address<label>*</label></span>
                        <input name="email" type="email"/>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="register-bottom-grid">
                    <h3>Login Information</h3>
                    <div>
                        <span>Password<label>*</label></span>
                        <input name="password"  type="password"/>
                    </div>
                    <div>
                        <span>Confirm Password<label>*</label></span>
                        <input name="repassword" type="password"/>
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
