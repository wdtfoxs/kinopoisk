package ru.springproject.core.dz.repositories.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.springproject.core.dz.entity.User;
import ru.springproject.core.dz.repositories.UserRepository;


@Repository
public class UserRepositoryHibernate implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session curSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addUser(User user) {
        curSession().save(user);
    }

    @Override
    public User getUseByUsername(String username){
        return (User) curSession().createCriteria(User.class)
                .add(Restrictions.eq("username", username)).uniqueResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return (User) curSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public User getUserByVkID(Integer id) {
        return (User) curSession().createCriteria(User.class)
                .add(Restrictions.eq("vkontakte_id", id)).uniqueResult();
    }
}
