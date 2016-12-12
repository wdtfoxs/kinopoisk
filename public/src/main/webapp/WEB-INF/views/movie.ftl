<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "template.ftl">
<@mainTemplate title="${movie.name}"/>
<#macro m_body>
<div class="content">
    <div class="movie_top">
        <div class="col-md-9 movie_box">
            <div class="grid images_3_of_2">
                <div class="movie_image">
                    <span class="movie_rating">5.0</span>
                    <img src="${movie.image}" class="img-responsive" alt=""/>
                </div>
                <div class="movie_rate">
                    <@sec.authorize access="isAuthenticated()">
                        <div class="rating_desc"><p>Your Vote :</p></div>
                        <@form.form commandName="ratingForm" action="/rating" acceptCharset="UTF-8" method="post" cssClass="sky-form">
                            <fieldset>
                                <section>
                                    <div class="rating">
                                        <@form.radiobutton path="rating" id="stars-rating-5" value="5"/>
                                        <label for="stars-rating-5"><i class="icon-star"></i></label>
                                        <@form.radiobutton path="rating" id="stars-rating-4" value="4"/>
                                        <label for="stars-rating-4"><i class="icon-star"></i></label>
                                        <@form.radiobutton path="rating" id="stars-rating-3" value="3"/>
                                        <label for="stars-rating-3"><i class="icon-star"></i></label>
                                        <@form.radiobutton path="rating" id="stars-rating-2" value="2"/>
                                        <label for="stars-rating-2"><i class="icon-star"></i></label>
                                        <@form.radiobutton path="rating" id="stars-rating-1" value="1"/>
                                        <label for="stars-rating-1"><i class="icon-star"></i></label>
                                    </div>
                                </section>
                            </fieldset>
                            <input type="hidden" name="movieid" value="${movie.id}">
                            <input type="hidden" name="userid"
                                   value="<@sec.authentication property="principal.user.id"/>">
                            <input type="submit" value="go" id="submit" style="display:none;">
                        </@form.form>
                    </@sec.authorize>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="desc1 span_3_of_2">
                <p class="movie_option"><strong>Name: </strong><a href="#">${movie.name}</a></p>
                <p class="movie_option"><strong>Year: </strong>${movie.year}</p>
                <p class="movie_option"><strong>Genre: </strong>
                    <#list movie.genres as g>
                        <a href="#">${g.name}</a>,
                    </#list>
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
                <p class="movie_option"><strong>Age restriction: </strong>${movie.age}</p>
            <#--<div class="down_btn"><a class="btn1" href="#"><span> </span>Download</a></div>-->
            </div>
            <div class="clearfix"></div>
            <p class="m_4">${movie.description}</p>
            <h2>Трейлер к фильму</h2>
            <p class="m_4">
                <iframe width="560" height="315" src="${movie.trailer}" frameborder="0" allowfullscreen></iframe>
            </p>
            <@sec.authorize access="isAuthenticated()">
                <#if existReview == false>
                    <form method="post" action="/review/add">
                        <div class="text">
                            <textarea name="review" placeholder="Review"></textarea>
                        </div>
                        <div class="form-submit1">
                            <input name="submit" type="submit" id="submit" value="Submit Your Review"><br>
                        </div>
                        <input type="hidden" name="movieid" value="${movie.id}">
                        <input type="hidden" name="userid" value="<@sec.authentication property="principal.user.id"/>">
                        <div class="clearfix"></div>
                    </form>
                </#if>
            </@sec.authorize>
            <div class="single">
                <#if !(movie.reviews)??>
                    <h1>К этому фильму еще не написано рецензий</h1>
                <#else>
                    <h1>${movie.reviews?size} Comments</h1>
                    <ul class="single_list">
                        <#list movie.reviews as r>

                            <li>
                                <div class="preview"><a href="#"><img src="${r.user.photo}" class="img-responsive"
                                                                      alt=""></a></div>
                                <div class="data">
                                    <div class="title">${r.user.username} / ${r.date} </div>
                                <#--/ <a href="#">reply</a>-->
                                    <p>${r.review}</p>
                                </div>
                                <div class="clearfix"></div>
                            </li>
                        </#list>
                    </ul>
                </#if>
            </div>
        </div>
        <div class="col-md-3">
            <div class="movie_img">
                <div class="grid_2">
                    <img src="../../resources/images/pic6.jpg" class="img-responsive" alt="">
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
                <img src="../../resources/images/pic2.jpg" class="img-responsive" alt="">
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
                <img src="../../resources/images/pic9.jpg" class="img-responsive" alt="">
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
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function (){
        var but5 = document.getElementById("stars-rating-5");
        var but4 = document.getElementById("stars-rating-4");
        var but3 = document.getElementById("stars-rating-3");
        var but2 = document.getElementById("stars-rating-2");
        var but1 = document.getElementById("stars-rating-1");
        but5.addEventListener("click", submitRating);
        but4.addEventListener("click", submitRating);
        but3.addEventListener("click", submitRating);
        but2.addEventListener("click", submitRating);
        but1.addEventListener("click", submitRating);

        function submitRating() {
            document.getElementById("submit").click();
        }
    });
</script>
</#macro>