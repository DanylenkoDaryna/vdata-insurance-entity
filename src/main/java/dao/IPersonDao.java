package dao;
import data.InsuredPerson;

import java.util.ArrayList;
import java.util.List;

public interface IPersonDao<InsuredPerson> {
    void create(InsuredPerson entity, int index);

    InsuredPerson read(long id);

    ArrayList <InsuredPerson> readList(long id, ArrayList <InsuredPerson> o);

    void update(InsuredPerson entity);

    void delete(long id);
}
