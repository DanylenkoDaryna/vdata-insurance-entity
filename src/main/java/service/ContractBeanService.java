package service;

import dao.IContractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Operation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class ContractBeanService implements IContractService {

    @Autowired
    private IContractDao dao;

    @Override
    public IContractDao<Contract> getDao() {
        return dao;
    }

    public void setDao(IContractDao operation) {
        dao = operation;
    }

    @Transactional(readOnly = false)
    public void doCreate(Contract bean, ContractBuilder cb) {
        dao.create(bean, cb);
    }

    @Transactional(readOnly = false)
    public void doCreate2(Contract bean) {
        dao.create(bean);
    }

    @Transactional(readOnly = true)
    public void doRead(Contract bean) {
        dao.read(bean.getId());
    }

    @Transactional(readOnly = false)
    public void doUpdate(Contract bean, ContractBuilder cb) {
        dao.update(bean, cb);
    }

    @Transactional(readOnly = false)
    public void doDelete(Contract bean) {
        dao.delete(bean.getId());
    }


}
