package ru.kpfu.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Vlad.M on 23.11.2016.
 */
public interface MyService<T> {
    void add(T item);

    void delete(Long id);

    void update(T item);

    List<T> matchQuery(String q, Pageable page);

    List<T> matchPhraseQuery(String q);

    List<T> matchPhrasePrefixQuery(String q);

    List<T> fuzzyQuery(String q);

    String autocomplete(String q);
}
