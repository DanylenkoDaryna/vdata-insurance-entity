package data;

import dict.Type;

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
    private String city;
    private String street;
    private String building;


    /**
     * @param pers  enum that specify one of types of person ENTITY or NATURAL
     * @param names FIO(first, middle, last name)(for NATURAL person)/name of the organization(for ENTITY person)
     *   residential address
     */
    public Client(Type pers, String names, String city, String street, String building) {
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
        this.setPerson(Type.NATURAL);
        this.setName("Noname Nonamov Nonam");
        city="";
        street="";
        building="";
    }

    @Override
    public String toString() {
        return "Person:" + this.getPerson() + "\tName:" + this.getName() + "\tAdress:" + this.getCity()+", st. "+
                this.getStreet()+", "+this.getBuilding();
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



    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    private void setBuilding(String building) {
        this.building = building;
    }
}



