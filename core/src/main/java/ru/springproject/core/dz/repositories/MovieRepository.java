package ru.springproject.core.dz.repositories;

import ru.springproject.core.dz.entity.Movie;

public interface MovieRepository {
    Movie getMovieById(Long id);
}
