package service;

import data.Client;
import data.InsuredPerson;

import java.time.LocalDate;
import java.util.ArrayList;



public class ContractBuilder implements IBuilder {

    private int id;
    private LocalDate acceptDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Client man;
    private ArrayList<InsuredPerson> personList=new ArrayList<>();


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setAcceptDate(LocalDate acceptDate) {
        this.acceptDate = acceptDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setClient(Client man) {
        this.man = man;
    }

    @Override
    public void setPersons(ArrayList<InsuredPerson> personList) {
        this.personList = personList;
    }

    public Contract getResult() {

        return new Contract(id,acceptDate,startDate,endDate,man,personList);
    }
}
