package service;

import data.Client;


import java.time.LocalDate;
import java.util.ArrayList;


public class Director {

    public void constructContract(IBuilder builder) {
        builder.setId(41);
        builder.setAcceptDate(LocalDate.of(203456, 9, 1));
        builder.setStartDate(LocalDate.of(24334, 10, 1));
        builder.setEndDate(LocalDate.of(2024440, 10, 1));
        builder.setClient(new Client());
        builder.setPersons(new ArrayList<>());
    }
}
