<#include "template.ftl">
<@mainTemplate title="Логин"/>
<#macro m_body>
<div class="content">
    <div class="register">
        <div class="col-md-6 login-left">
            <h3>New Customers</h3>
            <p>By creating an account with our store, you will be able to move through the checkout process faster,
                store multiple shipping addresses, view and track your orders in your account and more.</p>
            <a class="acount-btn" href="/reg">Create an Account</a>
        </div>
        <div class="col-md-6 login-right">
            <h3>Registered Customers</h3>
            <p>If you have an account with us, please log in.</p>
            <form method="post" action="/login">
                <div>
                    <span>Username<label>*</label></span>
                    <input type="text" name="email">
                </div>
                <div>
                    <span>Password<label>*</label></span>
                    <input type="password" name="password">
                </div>
                <#if error?has_content>
                    <div style="color: #d8232a;" class="col-sm-offset-2 col-sm-10">Имя пользователя и пароль не подходят</div>
                </#if>
                <a class="news-letter" href="#">
                    <label class="checkbox"><input type="checkbox" name="_spring_security_remember_me"><i> </i>Sign Up for Newsletter</label>
                </a>
                <input type="submit" value="Login">
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
</#macro>