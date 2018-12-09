package service;

import dao.IContractDao;

public class ContractService implements IContractService {
    @Override
    public IContractDao<Contract> getDao() {
        return new ContractDao();
    }
}
