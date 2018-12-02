package service;

import data.Client;
import data.InsuredPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public interface IBuilder {

    void setId(int id);
    void setAcceptDate(LocalDate acceptDate);
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);
    void setClient(Client man);
    void setPersons(ArrayList<InsuredPerson> personList);

}
