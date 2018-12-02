package service;

import data.InsuredPerson;

import java.util.List;

public interface IContract {

    double getTotalCost();
    List<InsuredPerson> sortPersonsByDate(List<InsuredPerson> persons);
    List<InsuredPerson> sortPersonsByName(List<InsuredPerson> persons);
    InsuredPerson getPersonById(int i);
    void saveCSV();
    Contract uploadCSV();
}
