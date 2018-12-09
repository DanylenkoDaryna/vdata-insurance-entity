package dao;

import data.Client;

public interface IClientDao<Client> {
    void create(Client entity);

    Client read(long id);

    void update(Client entity);

    void delete(long id);

    void insert(Client man, int id);
}
