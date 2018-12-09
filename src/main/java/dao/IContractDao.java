package dao;
import service.Contract;
import service.ContractBuilder;

public interface IContractDao<Contract> {
    void create(Contract entity, ContractBuilder contractBuilder);

    Contract read(long id);

    void update(Contract entity, ContractBuilder contractBuilder);

    void delete(long id);
}
