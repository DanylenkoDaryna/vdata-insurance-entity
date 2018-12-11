package data;

import dict.Type;


import java.io.*;
import java.time.LocalDate;

/**
 * This class is used to contain the main info about Client that initiate the contract: type of person, FIO(first,
 * middle, last name), address
 *
 * @author Daryna
 */
public class Client implements Serializable {

    private int id;
    private Type person;
    private String name;
    private String middleName;
    private String surname;
    private String city;
    private String street;
    private String building;



    public Client(Type pers, String name, String middleName, String surname, String city, String street, String building, int id) {
        this.setId(id);
        this.setPerson(pers);
        this.setName(name);
        this.setMiddleName(middleName);
        this.setSurname(surname);
        this.setCity(city);
        this.setStreet(street);
        this.setBuilding(building);

    }


    /**
     * @param pers  enum that specify one of types of person ENTITY or NATURAL
     * @param names FIO(first, middle, last name)(for NATURAL person)/name of the organization(for ENTITY person)
     * @param id
     */
    public Client(Type pers, String names, String city, String street, String building, int id) {
        this.setId(id);
        this.setPerson(pers);
        this.setName(names);
        this.setCity(city);
        this.setStreet(street);
        this.setBuilding(building);

    }

    /**
     * Constructs a new Client with the default parameters
     * Used for avoiding NullPointerExeption
     */
    public Client() {
        this.id = 0;
        this.setPerson(Type.NATURAL);
        this.setName("Noname Nonamov Nonam");
        city="";
        street="";
        building="";
    }

    @Override
    public String toString() {
        return "Person:" + this.getPerson() + "\tName:" + this.getName() + " " + this.getMiddleName()
                + " " + this.getSurname() +"\tAdress:" + this.getCity()+", st. "+
                this.getStreet()+", "+this.getBuilding();
    }


    public String localDateAsString() {


        return middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }


}



