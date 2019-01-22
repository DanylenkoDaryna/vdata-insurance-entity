package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class HibernateUnivesalDao {
    @Autowired
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUnivesalDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T getById(Serializable id, Class<T> clazz) {
        return getCurrentSession().get(clazz, id);
    }

    public <T> T save(T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
