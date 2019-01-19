package service;

import dao.IClientDao;
import data.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;


public class ClientBeanService implements IClientService {

    @Autowired
    private IClientDao dao;

    @Override
    public IClientDao<Client> getDao() {
        return this.dao;
    }


    public void setDao(IClientDao<Client> operation) {
        this.dao = operation;
    }


    public void doCreate(Client bean) {
        dao.create(bean);
    }


    public void doRead(Client bean) {
        dao.read(bean.getId());
    }


    public void doUpdate(Client bean) {
        dao.update(bean);
    }


    public void doDelete(Client bean) {
        dao.delete(bean.getId());
    }

}
