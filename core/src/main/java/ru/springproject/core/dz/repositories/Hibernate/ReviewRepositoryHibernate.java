package ru.springproject.core.dz.repositories.Hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.springproject.core.dz.entity.Movie;
import ru.springproject.core.dz.entity.Review;
import ru.springproject.core.dz.entity.User;
import ru.springproject.core.dz.repositories.ReviewRepository;


@Repository
public class ReviewRepositoryHibernate implements ReviewRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ReviewRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Review getReview(User user, Movie movie) {
        Query query = currSession().createQuery("from Review where user = :userId and movie = :movieId");
        query.setParameter("userId", user);
        query.setParameter("movieId", movie);
        return (Review) query.uniqueResult();
    }

        @Override
        public void addReview (Review review){
            currSession().save(review);
        }
    }
