package ua.profitsoft.entity;

import java.io.*;

/**
 * This class is used to contain the main info about Client that initiate the contract: type of person, FIO(first,
 * middle, last name), address
 *
 * @author Daryna
 */
public class Client implements Serializable {

    private Type person;
    private String name;
    private String adress;


    /**
     * @param pers  enum that specify one of types of person ENTITY or NATURAL
     * @param names FIO(first, middle, last name)(for NATURAL person)/name of the organization(for ENTITY person)
     * @param adr   residential address
     */
    Client(Type pers, String names, String adr) {
        this.setPerson(pers);
        this.setName(names);
        this.setAdress(adr);
    }

    /**
     * Constructs a new Client with the default parameters
     * Used for avoiding NullPointerExeption
     */
    Client() {
        this.setPerson(Type.NATURAL);
        this.setName("Noname Nonamov Nonam");
        this.setAdress("noHome");
    }

    @Override
    public String toString() {
        return "Person:" + this.getPerson() + "\tName:" + this.getName() + "\tAdress:" + this.getAdress();
    }

    public Type getPerson() {
        return person;
    }

    private void setPerson(Type person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    private void setAdress(String adress) {
        this.adress = adress;
    }
}



