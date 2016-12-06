package ru.springproject.core.dz.repositories.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.springproject.core.dz.entity.Movie;
import ru.springproject.core.dz.repositories.MovieRepository;

@Repository
public class MovieRepositoryHibernate implements MovieRepository{

    private final SessionFactory sessionFactory;

    @Autowired
    public MovieRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Movie getMovieById(Long id) {
        return (Movie) currSession().get(Movie.class, id);
    }
}
