package ru.springproject.core.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springproject.core.dz.entity.Movie;
import ru.springproject.core.dz.repositories.MovieRepository;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Movie getMovieById(Long id){
        return movieRepository.getMovieById(id);
    }
}
