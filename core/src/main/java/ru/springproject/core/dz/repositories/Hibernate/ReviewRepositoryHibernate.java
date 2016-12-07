package ru.springproject.core.dz.repositories.Hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.springproject.core.dz.entity.Review;
import ru.springproject.core.dz.repositories.ReviewRepository;


import java.util.List;


@Repository
public class ReviewRepositoryHibernate implements ReviewRepository{

    private SessionFactory sessionFactory;

    @Autowired
    public ReviewRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Review getReview(Long movieId, Long userId) {
        Query query = currSession().createSQLQuery("SELECT * FROM review WHERE user_id = :userId AND movie_id = :movieId")
                .addEntity(Review.class)
                .setLong("userId", userId)
                .setLong("movieId", movieId);
        List list = query.list();
        if (list.size() == 0){
            return null;
        }else {
            return (Review) list.get(0);
        }
    }

    @Override
    public void addReview(Review review) {
        currSession().save(review);
    }
}
