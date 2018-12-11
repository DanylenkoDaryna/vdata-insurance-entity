package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * *This class is used to contain the main info about Insured Persons that mentioned in the contract:  unique id, FIO
 * (first, middle, last name), personal cost of Insurance for person, date of birth
 *
 * @author Daryna
 */
public class InsuredPerson implements  Serializable {
    private int id;
    private int number=0;
    private String name="no";
    private String middleName="no";
    private String surname="no";
    private LocalDate btdate;
    private double personalCost;
    private String localDateAsString;


    /**
     * Constructs a new Insured Person without parameters
     * Used for avoiding nullPointerExeption
     */
    public InsuredPerson() {
        this.setId(0);
        this.setName("Noname");
        this.setMiddleName("Anonim");
        this.setSurname("Fox");
        this.setBtdate(LocalDate.of(0, 1, 1));
        this.setPersonalCost(0.0);
    }

    /**
     * Constructs a new Insured Person with the specified parameters of this person
     *  @param id     int unique identifier for the person
     * @param name   String FIO(first, middle, last name)
     * @param btdate LocalDate of birth
     * @param cost   double personal cost of Insurance
     * @param surname
     */
    public InsuredPerson(int id, String name,String middleName, String surname, LocalDate btdate, double cost) {
        this.setId(id);
        this.setName(name);
        this.setMiddleName(middleName);
        this.setSurname(surname);

        this.setBtdate(btdate);
        this.setPersonalCost(cost);

    }

    /**
     * Overrided method toString() that prints the main info about object InsuredPerson in readable state
     *
     * @return String with all the attributes of class InsuredPerson: FIO, date of birth, personal cost of Insurance
     */
    @Override
    public String toString() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "id: " + this.getId() + "\tFIO:" + outFIO(this.getSurname(),this.getName(),this.getMiddleName()) + "\tDate:" +
                this.getBtdate().format(form) + "\tCost:" + this.getPersonalCost();
    }


    /**
     * Method for returning Insured person`s FIO in type "Ivanov O.O."
     *
     * @param name String with FIO() of Person
     * @return String in type "Ivanov O.O."
     */
    public String outFIO(String surname,String name,String middle) {

       return surname+" "+name.charAt(0)+"."+middle.charAt(0)+".";

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getBtdate() {
        return btdate;
    }

    public void setBtdate(LocalDate btdate) {
        this.btdate = btdate;
    }

    public double getPersonalCost() {
        return personalCost;
    }

    public void setPersonalCost(double personalCost) {
        this.personalCost = personalCost;
    }

    public String getLocalDateAsString() {
        return localDateAsString;
    }

    public void setLocalDateAsString(String localDateAsString) {

        String[]str=localDateAsString.split("-");
        LocalDate ld1=LocalDate.of(Integer.parseInt(str[0]),Integer.parseInt(str[1]),
                Integer.parseInt(str[2]));
        this.setBtdate(ld1);
        this.localDateAsString = localDateAsString;
    }
}
