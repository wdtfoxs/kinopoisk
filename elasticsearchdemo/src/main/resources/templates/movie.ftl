<#include "template.ftl">
<@mainTemplate title="${movie.name}"/>
<#macro m_body>
<script src="/resources/js/test.js"></script>
<link href="/resources/css/rating.css" rel="stylesheet">
<script src="/resources/js/rating.js"></script>
<div class="content">
<div class="movie_top">
<#if movie??>
<div class="col-md-9 movie_box">
<#--<h3>-->
<#--<#if movie.name??>-->
<#--${movie.name}-->
<#--</#if>-->
<#--</h3>-->
    <div class="grid images_3_of_2">
        <div class="movie_image">
            <span class="movie_rating" id="mv_rating">
                <#if movie.voted_number??>
                    ${movie.getAbsoluteRating()}
                    <#else >
                    0.0
                </#if>
            </span>
        <#--<#if movie.image??>-->
            <img src="${movie.image}" class="img-responsive" alt=""/>
        <#--<#else>-->
        <#--<img src="/resources/images/default_film.jpg" class="img-responsive" alt=""/>-->
        <#--</#if>-->
        </div>
        <div class="movie_rate">
            <div class="rating_desc"><p>Rating :</p></div>
            <form action="" class="sky-form">
                <#if canvote == 1>
                    <fieldset class="rating2" id="rating">
                        <input type="radio" id="star5" name="rating" value="5" onclick="rate5(${movie.id})"/><label class="full" for="star5"
                                                                                       title="Awesome - 5 stars"></label>
                        <input type="radio" id="star4" name="rating" value="4"
                        onclick="rate4(${movie.id})"/><label class="full" for="star4"
                                                                                       title="Pretty good - 4 stars"></label>
                        <input type="radio" id="star3" name="rating" value="3"
                        onclick="rate3(${movie.id})"/><label class="full" for="star3"
                                                                                       title="Meh - 3 stars"></label>
                        <input type="radio" id="star2" name="rating" value="2"
                        onclick='rate2(${movie.id})'/><label class="full" for="star2"
                                                                                       title="Kinda bad - 2 stars"></label>
                        <input type="radio" id="star1" name="rating" value="1"
                        onclick="rate1(${movie.id})"/><label class="full" for="star1" title="Sucks big time - 1 star"></label>

                    </fieldset>
                <#else >
                    <fieldset class="rating2">

                    <#--<#list 1..movie.getRealRating() as r>-->
                                            <#--<span class="glyphicon glyphicon-star" style="color: greenyellow"></span>-->
                                        <#--</#list>-->

                    </fieldset>
                </#if>
            </form>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="desc1 span_3_of_2">
        <p class="movie_option"><strong>Name: </strong><a href="#">${movie.name}</a></p>
        <p class="movie_option"><strong>Year: </strong>
        <#if movie.year??>
            ${movie.year}</p>
        </#if>
        <p class="movie_option"><strong>Genre: </strong>
            <#if movie.genres??>
                <#list movie.genres as g>
                    ${g}
                </#list>
            </#if>
        </p>
        <p class="movie_option"><strong>Director: </strong>
            <#list movie.peoples as p>
                <#if p.role == "DIRECTOR">
                    <a href="#">${p.name} ${p.surname}</a>,
                </#if>
            </#list>
        </p>
        <p class="movie_option"><strong>Actors: </strong>
            <#list movie.peoples as p>
                <#if p.role == "ACTOR">
                    <a href="#">${p.name} ${p.surname}</a>,
                </#if>
            </#list>
        </p>
        <p class="movie_option"><strong>Scenarist: </strong>
            <#list movie.peoples as p>
                <#if p.role == "SCENARIST">
                    <a href="#">${p.name} ${p.surname}</a>,
                </#if>
            </#list>
        </p>
        <p class="movie_option"><strong>Producer: </strong>
            <#list movie.peoples as p>
                <#if p.role == "PRODUCER">
                    <a href="#">${p.name} ${p.surname}</a>,
                </#if>
            </#list>
        </p>
        <p class="movie_option"><strong>Age restriction: </strong>
        <#if movie.age??>
            ${movie.age}</p>
        </#if>
        <#--<div class="down_btn"><a class="btn1" href="#"><span> </span>Download</a></div>-->
    </div>
    <div class="clearfix"></div>
    <p class="m_4">
    <#if movie.description??>
        <#--<h5>Description</h5>-->
    ${movie.description}
    </#if>
    </p>
    <h2>Трейлер к фильму</h2>
    <p class="m_4">
        <iframe width="560" height="315" src="${movie.trailer}" frameborder="0" allowfullscreen></iframe>
    </p>
    <#if access == 1 >
        <div class="text">
            <textarea id="comment_text" name="review" placeholder="add your comment..."></textarea>
        </div>
        <div class="form-submit1">
            <input name="submit" type="submit" id="submit" value="Send"
                   onclick="addComment(${movie.id})"><br>
        </div>
        <div class="clearfix"></div>
    </#if>
    <div class="single">
        <h1>Comments</h1>
        <#if access == 0>
            <strong>Please, <a href="/login">authenticate</a> to add comments.</strong>
        </#if>
        <div id="comlist">
            <#if comments??>
                <#list comments as com>
                    <ul class="single_list">
                        <div class="panel panel-primary">
                            <div class="panel-body">
                            ${com.user.username}:${com.date}
                            </div>
                            <div class="panel-footer">${com.content}</div>
                        </div>
                    </ul>
                </#list>
            <#else >
                <h3>No comments</h3>
            </#if>
        </div>
    </div>
</div>
    <div class="col-md-3">
        <div class="movie_img">
            <div class="grid_2">
                <img src="/resources/images/pic6.jpg" class="img-responsive" alt="">
                <div class="caption1">
                    <ul class="list_5 list_7">
                        <li><i class="icon5"> </i>
                            <p>3,548</p></li>
                    </ul>
                    <i class="icon4 icon6 icon7"> </i>
                    <p class="m_3">Guardians of the Galaxy</p>
                </div>
            </div>
        </div>
        <div class="grid_2 col_1">
            <img src="/resources/images/pic2.jpg" class="img-responsive" alt="">
            <div class="caption1">
                <ul class="list_3 list_7">
                    <li><i class="icon5"> </i>
                        <p>3,548</p></li>
                </ul>
                <i class="icon4 icon7"> </i>
                <p class="m_3">Guardians of the Galaxy</p>
            </div>
        </div>
        <div class="grid_2 col_1">
            <img src="/resources/images/pic9.jpg" class="img-responsive" alt="">
            <div class="caption1">
                <ul class="list_3 list_7">
                    <li><i class="icon5"> </i>
                        <p>3,548</p></li>
                </ul>
                <i class="icon4 icon7"> </i>
                <p class="m_3">Guardians of the Galaxy</p>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
<#else>
    <h3>No Film found</h3>
</#if>

</div>
</div>
</#macro>