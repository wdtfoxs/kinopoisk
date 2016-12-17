<#include "template.ftl">
<#if award??>
    <@mainTemplate title="${award.name} ${award.year}"/>
<#else >
    <@mainTemplate title="Not found"/>
</#if>
<#macro m_body>
<div class="content">
    <div class="movie_top">
        <#if award??>
            <div class="col-md-9 movie_box">
                <div class="grid images_3_of_2">
                    <div class="movie_image">
                        <img src="${award.photo}" class="img-responsive" alt=""/>
                    </div>
                </div>
                <div class="desc1 span_3_of_2">
                    <p class="movie_option"><strong>Name: </strong>${award.name}</p>
                    <p class="movie_option"><strong>Description: </strong>${award.description}</p>
                    <p class="movie_option"><strong>Year: </strong>${award.year}</p>
                    <p class="movie_option"><strong>Movie: </strong>
                        <a href="/movie/${award.movie.id}">
                            ${award.movie.name}
                        </a>
                    </p>
                    <p class="movie_option"><strong>The person who won the award: </strong>
                        <a href="/actor/${award.people.id}">
                        ${award.people.name}
                        </a>
                    </p>
                    <#--<p class="movie_option"><strong>Movie: </strong>-->
                        <#--<#if people.movies??>-->
                            <#--<#list  people.movies as m>-->
                                <#--<a href="/movie/${m.id}">-->
                                <#--${m.name},-->
                                <#--</a>-->
                            <#--</#list>-->
                        <#--<#else >-->
                            <#--Not found movies with the given actor-->
                        <#--</#if>-->
                    <#--</p>-->
                    <#--<p class="movie_option"><strong>Awards: </strong>-->
                        <#--<#if people.awards??>-->
                            <#--<#list people.awards as a>-->
                                <#--<a href="/movie/${m.id}">-->
                                <#--${a.name} ${a.year},-->
                                <#--</a>-->
                            <#--</#list>-->
                        <#--<#else >-->
                            <#--The actor has no awards-->
                        <#--</#if>-->
                    <#--</p>-->
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        <#else>
            <h3>No Award found</h3>
        </#if>

    </div>
</div>
</#macro>