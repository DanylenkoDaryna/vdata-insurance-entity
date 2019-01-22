package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;

public class PersonHibernateService {

    @Autowired
    private HibernateUnivesalDao entityPersister;

    @Transactional
    public void save(Entity entity) {
        entityPersister.save(entity);
    }

}
