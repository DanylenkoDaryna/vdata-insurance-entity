package service;

import dao.IPersonDao;
import data.InsuredPerson;

public class PersonService implements IPersonService {
    @Override
    public IPersonDao<InsuredPerson> getDao() {
        return new PersonDao();
    }

    public PersonService(){}
}
