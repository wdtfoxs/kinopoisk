package ru.springproject.core.dz.repositories;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.springproject.core.dz.entity.Movie;

import java.util.List;

@Repository
public class HiberMovie {

    @Autowired
    private SessionFactory sessionFactory;

    private Session curSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Movie> getMovie(String text){
        Query query = curSession().createQuery("from Movie where upper(name) like :text").setMaxResults(10);
        query.setString("text", text.toUpperCase() + "%");
        List m = query.list();
        System.out.println(m.size());
//        for (int i = 0; i<query.list().size(); i++) {
//            System.out.println(((Movie)m.get(i)).getName());
//        }
        return query.list();
    }
}
