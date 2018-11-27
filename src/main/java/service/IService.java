package service;

import dao.IDao;

interface IService<T> {

    IDao<T> getDao();

    default void create(T entity) {
        getDao().create(entity);
    }

    default T read(long id) {
        return getDao().read(id);
    }

    default void update(T entity) {
        getDao().update(entity);
    }

    default void delete(long id) {
        getDao().delete(id);
    }

}
