package dao;
import service.Contract;
import service.ContractBuilder;

public interface IContractDao<Contract> {
    void create(Contract entity, ContractBuilder contractBuilder);

    void create(Contract entity);

    Contract read(long id);

    void update(Contract entity, ContractBuilder contractBuilder);

    void delete(long id);
}
