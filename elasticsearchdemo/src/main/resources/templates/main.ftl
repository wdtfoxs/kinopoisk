<html ng-app="movie">
<head>
    <title>Main Page</title>

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

    <script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
    <link href="/resources/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- start plugins -->

    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:100,200,300,400,500,600,700,800,900'
          rel='stylesheet' type='text/css'>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-sanitize.min.js"></script>
    <script src="/resources/js/main.js"></script>

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

</head>
<#include "header.ftl"/>

<body ng-controller="MovieController">
<div class="slider">
    <div class="callbacks_container">
        <ul class="rslides" id="slider">
            <li><img src="/resources/images/banner.jpg" class="img-responsive" alt=""/>
                <div class="button">
                    <a href="#" class="hvr-shutter-out-horizontal">Watch Now</a>
                </div>
            </li>
            <li><img src="/resources/images/banner1.jpg" class="img-responsive" alt=""/>
                <div class="button">
                    <a href="#" class="hvr-shutter-out-horizontal">Watch Now</a>
                </div>
            </li>
            <li><img src="/resources/images/banner2.jpg" class="img-responsive" alt=""/>
                <div class="button">
                    <a href="#" class="hvr-shutter-out-horizontal">Watch Now</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="banner_desc">
        <div class="col-md-9">
            <ul class="list_1">
                <li>Published <span class="m_1">Feb 20, 2015</span></li>
                <li>Updated <span class="m_1">Feb 20 2015</span></li>
                <li>Rating <span class="m_1"><img src="/resources/images/rating.png" alt=""/></span></li>
            </ul>
        </div>
        <div class="col-md-3 grid_1">
            <ul class="list_1 list_2">
                <li><i class="icon1"> </i>
                    <p>2,548</p></li>
                <li><i class="icon2"> </i>
                    <p>215</p></li>
                <li><i class="icon3"> </i>
                    <p>546</p></li>
            </ul>
        </div>
    </div>
</div>
<div class="content">
    <div class="box_1">
        <div class="row">
            <div class="container">
                <div class="col-md-6 form-inline">
                    <label for="search">Поиск:</label>
                    <input class="form-control" ng-model="searchInput" id="search" ng-change="search()"
                           ng-model-options="{ debounce: 300 }"/>
                </div>
                <div class="col-md-3">
                    <select class="form-control" ng-model="type" ng-change="search()">
                        <option value="MATCH">match</option>
                        <option value="MATCH_PHRASE">match phrase</option>
                        <option value="MATCH_PHRASE_PREFIX">match phrase prefix</option>
                        <option value="FUZZY">fuzzy</option>
                        <option value="AUTO">autocomplete</option>
                    </select>
                </div>
            <#--<div class="col-md-3">-->
            <#--<button ng-click="load()" class="btn btn-md btn-default">Импортировать все фильмы</button>-->
            <#--</div>-->

            </div>
        </div>

    </div>
    <div class="row">
        <div class="container">
            <div class="col-lg-9">
                <table class="table table-striped col-md-6">
                    <thead>

                    <td></td>
                    <td>Название</td>
                    <td>Описание</td>
                    <td></td>
                    </thead>
                    <tbody>
                    <tr ng-repeat="movie in movies">

                        <td><img ng-src="{{movie.image}}" class="thumbnail" width="100px" height="100px"></td>
                        <td ng-bind-html="movie.name"></td>
                        <td>{{movie.description}}</td>
                        <td>
                            <button class="btn btn-info" ng-click="go(movie)" >More>></button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "footer.ftl"/>
</body>
</html>