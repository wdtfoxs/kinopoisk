<#include "template.ftl">
<#if people??>
    <@mainTemplate title="${people.name} ${people.surname}"/>
<#else >
    <@mainTemplate title="Not found"/>
</#if>
<#macro m_body>
<div class="content">
    <div class="movie_top">
        <#if people??>
            <div class="col-md-9 movie_box">
                <div class="grid images_3_of_2">
                    <div class="movie_image">
                        <img src="${people.photo}" class="img-responsive" alt=""/>
                    </div>
                </div>
                <div class="desc1 span_3_of_2">
                    <p class="movie_option"><strong>Name: </strong>${people.name}</p>
                    <p class="movie_option"><strong>Surname: </strong>${people.surname}</p>
                    <p class="movie_option"><strong>Patronymic: </strong>
                        <#if people.patronymic??>
                            ${people.patronymic}
                        </#if>
                    </p>
                    <p class="movie_option"><strong>Role: </strong>${people.role}</p>
                    <p class="movie_option"><strong>Age: </strong>${people.age}</p>
                    <p class="movie_option"><strong>Movies: </strong>
                        <#if people.movies??>
                            <#list  people.movies as m>
                                <a href="/movie/${m.id}">
                                ${m.name},
                                </a>
                            </#list>
                        <#else >
                            Not found movies with the given actor
                        </#if>
                    </p>
                    <p class="movie_option"><strong>Awards: </strong>
                        <#if people.awards??>
                            <#list people.awards as a>
                                <a href="/award/${a.id}">
                                ${a.name} (${a.year} г.)
                                </a>,
                            </#list>
                        <#else >
                            The actor has no awards
                        </#if>
                    </p>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        <#else>
            <h3>No Actor found</h3>
        </#if>

    </div>
</div>
</#macro>