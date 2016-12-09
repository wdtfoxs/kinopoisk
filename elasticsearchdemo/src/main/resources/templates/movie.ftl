<#include "template.ftl">
<@mainTemplate title="Кино"/>
<#macro m_body>
<script src="/resources/js/test.js"></script>
<div class="content">
    <div class="movie_top">
        <#if movie??>
        <div class="col-md-9 movie_box">
            <h3>
                <#if movie.name??>
                        ${movie.name}
                    </#if>
            </h3>
            <div class="grid images_3_of_2">
                <div class="movie_image">
                    <span class="movie_rating">5.0</span>
                    <#if movie.image??>
                        <img src="${movie.image}" class="img-responsive" alt=""/>
                    <#else>
                        <img src="/resources/images/default_film.jpg" class="img-responsive" alt=""/>
                    </#if>
                </div>
                <div class="movie_rate">
                    <div class="rating_desc"><p>Your Vote :</p></div>
                    <form action="" class="sky-form">
                        <fieldset>
                            <section>
                                <div class="rating">
                                    <input type="radio" name="stars-rating" id="stars-rating-5">
                                    <label for="stars-rating-5"><i class="icon-star"></i></label>
                                    <input type="radio" name="stars-rating" id="stars-rating-4">
                                    <label for="stars-rating-4"><i class="icon-star"></i></label>
                                    <input type="radio" name="stars-rating" id="stars-rating-3">
                                    <label for="stars-rating-3"><i class="icon-star"></i></label>
                                    <input type="radio" name="stars-rating" id="stars-rating-2">
                                    <label for="stars-rating-2"><i class="icon-star"></i></label>
                                    <input type="radio" name="stars-rating" id="stars-rating-1">
                                    <label for="stars-rating-1"><i class="icon-star"></i></label>
                                </div>
                            </section>
                        </fieldset>
                    </form>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="desc1 span_3_of_2">
                <p class="movie_option"><strong>Country: </strong><a href="#">established</a>, <a href="#">USA</a>
                </p>
                <p class="movie_option"><strong>Year: </strong>2014</p>
                <p class="movie_option"><strong>Category: </strong>
                    <#if movie.genres??>
                        <#list movie.genres as g>
                        ${g}
                        </#list>
                    </#if>
                </p>
                <p class="movie_option"><strong>Release date: </strong><#if movie.genres??>
                    <#if movie.date??>
                        <#list movie.date as d>
                        ${d}
                        </#list>
                    </#if>
                </#if></p>
                <p class="movie_option"><strong>Workers: </strong><a href="#">
                    <#if movie.workers??>
                            <#list movie.workers as w>
                    ${w}
                    </#list>
                        </#if>
                <p class="movie_option"><strong>Age restriction: </strong>13</p>
                <div class="down_btn"><a class="btn1" href="#"><span> </span>Download</a></div>
            </div>
            <div class="clearfix"></div>
        <p class="m_4"><#if movie.description??>
            <h5>Description</h5>
        ${movie.description}

            </p>
            <#if access == 1 >
                    <div class="text">
                    <textarea id="comment_text" value="Message:" onfocus="this.value = '';"
                              onblur="if (this.value == '') {this.value = 'Message';}">Message:</textarea>
                    </div>
                    <div class="form-submit1">
                        <input name="submit" type="submit" id="submit" value="Submit Your Message"
                               onclick="addComment(${movie.id})"><br>
                    </div>
                    <div class="clearfix"></div>

            </#if>
            <div class="single">

                <h1>Comments</h1>
                <#if access == 0>
                    <strong>Please, <a href="/auth">authenticate</a> to add comments.</strong>
                </#if>
                <div id="comlist">
                <#if comments??>
                <#list comments as com>
                    <ul class="single_list">
                        <div class="panel panel-primary">
                            <div class="panel-body">
                                ${com.user.name}:${com.date}
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
        </#if>

    </div>
</div>
</#macro>