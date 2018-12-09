package service;

import dao.IPersonDao;
import data.InsuredPerson;

public interface IPersonService {
    IPersonDao<InsuredPerson> getDao();

    default void create(InsuredPerson entity, int index) {
        getDao().create(entity, index);
    }

    default InsuredPerson read(long id) {
        return getDao().read(id);
    }

    default void update(InsuredPerson entity) {
        getDao().update(entity);
    }

    default void delete(long id) {
        getDao().delete(id);
    }
}
