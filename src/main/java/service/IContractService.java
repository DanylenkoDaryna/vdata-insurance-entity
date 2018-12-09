package service;

import dao.IClientDao;
import dao.IContractDao;

interface IContractService<T> {

    IContractDao<Contract> getDao();

    default void create(Contract entity, ContractBuilder contractBuilder) {
        getDao().create(entity,contractBuilder);
    }

    default Contract read(long id) {
        return getDao().read(id);
    }

    default void update(Contract entity, ContractBuilder contractBuilder) {
        getDao().update(entity,contractBuilder);
    }

    default void delete(long id) {
        getDao().delete(id);
    }

}
