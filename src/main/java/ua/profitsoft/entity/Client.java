package ua.profitsoft.entity;

import java.io.*;

/**
 * This class is used to contain the main info about Client that initiate the contract: type of person, FIO(first,
 * middle, last name), address
 *
 * @author Daryna
 */
public class Client implements Serializable{

    private Type person;
    private String name;
    private String adress;


    /**
     * Constructs a new Client with the specified parameters about Client
     *
     * @param pers  enum that specify one of types of person ENTITY or NATURAL
     * @param names FIO(first, middle, last name)(for NATURAL person)/name of the organization(for ENTITY person)
     * @param adr   residential address
     */
    public Client(Type pers, String names, String adr) {
        this.setPerson(pers);
        this.setName(names);
        this.setAdress(adr);
    }

    /**Constructs a new Client with the default parameters
     * Used for avoiding NullPointerExeption
     */
    public Client(){
        this.setPerson(Type.NATURAL);
        this.setName("Noname Nonamov Nonam");
        this.setAdress("noHome");
    }

    /**
     * Overrided method toString() that prints the main info about object Client
     *
     * @return String with all the attributes of class in readable state
     */
    @Override
    public String toString() {
        return "Person:" + this.getPerson() + "\tName:" + this.getName() + "\tAdress:" + this.getAdress();
    }

    public Type getPerson() {
        return person;
    }

    public void setPerson(Type person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

/**
 * enum for determination the type of Client: LEGAL - legal, NATURAL - physical
 */
enum Type {
    NATURAL,
    LEGAL
}
