package service;

import dao.IPersonDao;
import data.InsuredPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class PersonBeanService implements IPersonService {

    @Autowired
    private IPersonDao dao;

    @Override
    public IPersonDao<InsuredPerson> getDao() {
        return dao;
    }

    public void setDao(IPersonDao operation) {
        dao = operation;
    }

    public void doCreate(InsuredPerson bean, int index) {
        dao.create(bean, index);
    }


    public void doRead(InsuredPerson bean) {
        dao.read(bean.getId());
    }


    public void doUpdate(InsuredPerson bean) {
        dao.update(bean);
    }


    public void doDelete(InsuredPerson bean) {
        dao.delete(bean.getId());
    }

}
