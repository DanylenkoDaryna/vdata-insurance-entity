package ua.profitsoft.entity;

import data.Client;
import data.InsuredPerson;
import dict.Type;
import service.Contract;
import service.ContractBuilder;
import service.Director;

import java.util.*;
import java.time.LocalDate;

/**
 * This class is used to show the initialization and filling the object Contract, with the display on the console
 * of the main attributes of contract and total insurance cost
 *
 * @author Daryna
 */
public class Demo {

    public static void main(String[] args){
       System.out.print("Contains in storage:");

        Client first = new Client(Type.LEGAL, "CyberLife", "Detroit", "Sumska","14-F" );
        ArrayList<InsuredPerson> firstList = new ArrayList<>();

        try {
            InsuredPerson a=new InsuredPerson(1, "Pavlik Viktor Nazarovich",
                    LocalDate.of(1995, 12, 1), 123.50);
            firstList.add(a);
            firstList.add(new InsuredPerson(2, "Chelentttano Adriana Petrovich", LocalDate.of(1971, 7, 20), 1150));
            firstList.add(new InsuredPerson(3, "Chaplin Adriano Petrovich", LocalDate.of(1971, 8, 20), 1150));
            firstList.add(new InsuredPerson(4, "Gosling Rayan Reinolds", LocalDate.of(1971, 8, 20), 1150));
            firstList.add(new InsuredPerson(5, "Sanchez Rick Richard", LocalDate.of(1974, 8, 20), 1150));
        }catch(NullPointerException n){
            n.printStackTrace();
            System.out.println("ffff");
        }

        Director director = new Director();
        ContractBuilder contractBuilder=new ContractBuilder();
        director.constructContract(contractBuilder);
        contractBuilder.setId(41);
        contractBuilder.setAcceptDate(LocalDate.of(2018, 9, 1));
        contractBuilder.setStartDate(LocalDate.of(2018, 10, 1));
        contractBuilder.setEndDate(LocalDate.of(2020, 10, 1));
        contractBuilder.setClient(first);
        contractBuilder.setPersons(firstList);


        Contract ID41 =contractBuilder.getResult();
        System.out.print(ID41.toString());
        System.out.println(ID41.getTotalCost());

        Client seckond = new Client(Type.NATURAL, "Petrichenko Anthon Victorovich", "Kharkyv",
                "Klochkovskaya", "111-A");
        ArrayList<InsuredPerson> secList = new ArrayList<>();
        secList.add(new InsuredPerson(1, "Ivashenko Inokentiy Nikolovich", LocalDate.of(1982, 10, 3), 220.55));
        secList.add(new InsuredPerson(2, "Danylchenko Dmythriy Horithonovich", LocalDate.of(1997, 12, 14), 49.99));
        secList.add(new InsuredPerson(3, "Prof IT Soft ", LocalDate.of(2002, 8, 7), 0.0));



        contractBuilder.setId(42);
        contractBuilder.setAcceptDate(LocalDate.of(2015, 8, 17));
        contractBuilder.setStartDate(LocalDate.of(2002, 9, 17));
        contractBuilder.setEndDate(LocalDate.of(2019, 9, 17));
        contractBuilder.setClient(seckond);
        contractBuilder.setPersons(secList);

        Contract ID42 = contractBuilder.getResult();
        System.out.print(ID42.toString());
        System.out.println(ID42.getTotalCost());

            ID42.saveCSV();

            System.out.println(ID42.uploadCSV().toString());

    }

}

