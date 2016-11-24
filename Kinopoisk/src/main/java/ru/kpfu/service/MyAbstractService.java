package ru.kpfu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.MyObject;

import java.util.List;

import static ru.kpfu.config.ElasticConfig.INDEX;

/**
 * Created by Vlad.M on 23.11.2016.
 */
@Service
public abstract class MyAbstractService<T extends MyObject> implements MyService<T> {
    @Autowired
    Client client;
    protected String DESCRIPTION_FIELD;
    protected String NAME_FIELD;
    protected String ALL_FIELD;
    protected String TYPE = "MOVIE";
    protected ObjectMapper mapper = new ObjectMapper();
    public void add(T item) {
        try {
            client.prepareIndex(INDEX, TYPE, String.valueOf(item.getId()))
                    .setSource(mapper.writeValueAsString(item))
                    .get();

        } catch (JsonProcessingException ignored) {
        }
    }

    public void delete(Long id) {

    }

    public void update(T item) {

    }

    public List<T> matchQuery(String q, Pageable page) {
        return null;
    }

    public List<T> matchPhraseQuery(String q) {
        return null;
    }

    public abstract List<T> matchPhrasePrefixQuery(String q) ;


    public List<T> fuzzyQuery(String q) {
        return null;
    }

    public String autocomplete(String q) {
        return null;
    }
    abstract List<T> getResult(SearchResponse response);

}
