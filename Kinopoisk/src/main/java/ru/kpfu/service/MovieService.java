package ru.kpfu.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.Movie;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.config.ElasticConfig.INDEX;

/**
 * Created by Vlad.M on 23.11.2016.
 */
@Service
public class MovieService extends MyAbstractService<Movie> {
    @PostConstruct
    public void init(){
        this.TYPE = "MOVIE";
    }

    @Override
    public List<Movie> matchPhrasePrefixQuery(String q) {
        SearchResponse response = client.prepareSearch(INDEX)
                .setTypes(TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchPhraseQuery(DESCRIPTION_FIELD, q))
                .execute()
                .actionGet();

        return getResult(response);
    }

    protected List<Movie> getResult(SearchResponse response) {
        List<Movie> result = new ArrayList<>();
        response.getHits().forEach(h -> {
            try {
                result.add(mapper.readValue(h.getSourceAsString(), Movie.class));
            } catch (IOException ignored) {
            }
        });
        return result;
    }
}
