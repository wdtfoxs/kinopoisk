<#macro mainTemplate title="HomePage" styles=[] scripts=[] >
<!DOCTYPE HTML>
    <#if title="Главная">
    <html ng-app="movie">
    <#else>
    <html>
    </#if>
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Movie_store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <link href="/resources/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- start plugins -->
    <script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:100,200,300,400,500,600,700,800,900'
          rel='stylesheet' type='text/css'>
    <#if title == "Главная">
        <script src="/resources/js/responsiveslides.min.js"></script>
        <script>
            $(function () {
                $("#slider").responsiveSlides({
                    auto: true,
                    nav: true,
                    speed: 500,
                    namespace: "callbacks",
                    pager: true
                });
            });
        </script>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:100,200,300,400,500,600,700,800,900'
              rel='stylesheet' type='text/css'>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-sanitize.min.js"></script>
        <script src="/resources/js/main.js"></script>

        <script src="/resources/js/responsiveslides.min.js"></script>
    </#if>
    <#if title="Регистрация" || title="Логин">
        <link href="/resources/css/bootstrap-social.css" rel="stylesheet">
        <link href="/resources/css/docs.css" rel="stylesheet">
        <link href="/resources/css/font-awesome.css" rel="stylesheet">
    </#if>
</head>
    <#if title="Главная">
        <body ng-controller="MovieController">
    <#else>
        <body>
    </#if>
<div class="container">
    <div class="container_wrap">
        <#include "header.ftl">
        <@m_body/>
    </div>
</div>
    <#include "footer.ftl">
</body>
</html>
</#macro>