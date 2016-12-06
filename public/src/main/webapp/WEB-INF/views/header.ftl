<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<div class="header_top">
    <div class="col-sm-3 logo"><a href="/"><img src="../../resources/images/logo.png" alt=""/></a></div>
    <div class="col-sm-6 nav">
        <ul>
            <li><span class="simptip-position-bottom simptip-movable" data-tooltip="comic"><a
                    href="movie.html"> </a></span></li>
            <li><span class="simptip-position-bottom simptip-movable" data-tooltip="movie"><a
                    href="movie.html"> </a> </span></li>
            <li><span class="simptip-position-bottom simptip-movable" data-tooltip="video"><a
                    href="movie.html"> </a></span></li>
            <li><span class="simptip-position-bottom simptip-movable" data-tooltip="game"><a
                    href="movie.html"> </a></span></li>
            <li><span class="simptip-position-bottom simptip-movable" data-tooltip="tv"><a
                    href="movie.html"> </a></span></li>
            <li><span class="simptip-position-bottom simptip-movable" data-tooltip="more"><a
                    href="movie.html"> </a></span></li>
        </ul>
    </div>
    <div class="col-sm-3 header_right">
        <@sec.authorize access="isAnonymous()">
        <ul class="header_right_box anon">
            <li><p><a href="/registration">Sign up</a></p></li>
            <li><p> | </p></li>
            <li><p><a href="/login">Log in</a></p></li>
        </@sec.authorize>
        <@sec.authorize access="isAuthenticated()">
        <ul class="header_right_box auth">
            <li><img src="<@sec.authentication property="principal.user.photo"/>" alt=""/></li>
            <li><p><a href="/"><@sec.authentication property="principal.username"/></a></p></li>
            <li><p> | </p></li>
            <li><p><a href="/logout">Log out</a></p></li>
        </@sec.authorize>
        <#--<li class="last"><i class="edit"> </i></li>-->
            <div class="clearfix"></div>
        </ul>
    </div>
    <div class="clearfix"></div>
</div>