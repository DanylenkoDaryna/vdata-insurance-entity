package service;


import dao.IClientDao;
import data.Client;

public interface IClientService {
    IClientDao<Client> getDao();

    default void create(Client entity) {
        getDao().create(entity);
    }

    default Client read(long id) {
        return getDao().read(id);
    }

    default void update(Client entity) {
        getDao().update(entity);
    }

    default void delete(long id) {
        getDao().delete(id);
    }
}
