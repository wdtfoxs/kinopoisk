<#include "template.ftl">
<@mainTemplate title="Логин"/>
<#macro m_body>
<div class="content">
    <div class="register">
        <div class="col-md-6 login-left">
            <h3>New Customers</h3>
            <p>By creating an account with our store, you will be able to move through the checkout process faster,
                store multiple shipping addresses, view and track your orders in your account and more.</p>
            <a class="acount-btn" href="/registration">Create an Account</a>
        </div>
        <div class="col-md-6 login-right">
            <h3>Registered Customers</h3>
            <p>If you have an account with us, please log in.</p>
            <form>
                <div>
                    <span>Email Address<label>*</label></span>
                    <input type="text">
                </div>
                <div>
                    <span>Password<label>*</label></span>
                    <input type="text">
                </div>
                <a class="forgot" href="#">Forgot Your Password?</a>
                <input type="submit" value="Login">
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
</#macro>