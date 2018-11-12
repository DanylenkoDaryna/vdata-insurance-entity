package ua.profitsoft.entity;

import java.util.*;
import java.time.LocalDate;

/**
 * This class is used to show the initialization and filling the object Contract, with the display on the console
 * of the main attributes of contract and total insurance cost
 *
 * @author Daryna
 */
public class Demo {

    public static void main(String args[]) {
        System.out.print("Contains in storage:");

        Client first = new Client(Type.LEGAL, "\"StarLife\"", "sq. Constitutciy 17");
        ArrayList<InsuredPerson> firstList = new ArrayList<>();

        firstList.add(new InsuredPerson(1, "Pavlik Viktor Nazarovich", LocalDate.of(1995, 12, 01), 123.50));
        firstList.add(new InsuredPerson(2, "Chelentano Adriana Petrovich", LocalDate.of(1971, 7, 20), 1150));
        firstList.add(new InsuredPerson(3, "Chaplin Adriano Petrovich", LocalDate.of(1971, 8, 20), 1150));
        firstList.add(new InsuredPerson(4, "Gosling Rayan Reinolds", LocalDate.of(1971, 8, 20), 1150));
        firstList.add(new InsuredPerson(5, "Sanchez Rick Richard", LocalDate.of(1974, 8, 20), 1150));
        Contract ID41 = new Contract(41, LocalDate.of(2018, 9, 01),
                LocalDate.of(2018, 10, 01), LocalDate.of(2020, 10, 1),
                first, firstList);
        System.out.print(ID41.toString());
        ID41.TotalCost();

        Client seckond = new Client(Type.NATURAL, "Petrichenko Anthon Victorovich", "st. Klochkovskaya, 111-A");
        ArrayList<InsuredPerson> secList = new ArrayList<>();
        secList.add(new InsuredPerson(1, "Ivashenko Inokentiy Nikolovich", LocalDate.of(1982, 10, 3), 220.55));
        secList.add(new InsuredPerson(2, "Danylchenko Dmythriy Horithonovich", LocalDate.of(1997, 12, 14), 49.99));
        secList.add(new InsuredPerson(3, "Prof IT Soft ", LocalDate.of(2002, 8, 7), 0.0));

        Contract ID42 = new Contract(42, LocalDate.of(2015, 8, 17),
                LocalDate.of(2002, 9, 17), LocalDate.of(2019, 9, 17),
                seckond, secList);
        System.out.print(ID42.toString());
        ID42.TotalCost();

    }

}

