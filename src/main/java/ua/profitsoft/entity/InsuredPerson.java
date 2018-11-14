package ua.profitsoft.entity;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * *This class is used to contain the main info about Insured Persons that mentioned in the contract:  unique id, FIO
 * (first, middle, last name), personal cost of Insurance for person, date of birth
 *
 * @author Daryna
 */
public class InsuredPerson implements Comparable {
    private int id;
    private String flname;
    private LocalDate btdate;
    private double personalCost;


    public InsuredPerson() {

    }

    /**
     * Constructs a new Insured Person with the specified parameters of this person
     *
     * @param id     int unique identifier for the person
     * @param name   String FIO(first, middle, last name)
     * @param btdate LocalDate of birth
     * @param cost   double personal cost of Insurance
     */
    public InsuredPerson(int id, String name, LocalDate btdate, double cost) {
        this.setId(id);
        this.setFlname(name);
        this.setPersonalCost(cost);
        this.setBtdate(btdate);
    }

    /**
     * Overrided method toString() that prints the main info about object InsuredPerson in readable state
     *
     * @return String with all the attributes of class InsuredPerson: FIO, date of birth, personal cost of Insurance
     */
    @Override
    public String toString() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "id: " + this.getId() + "\tFIO:" + outFIO(this.getFlname()) + "\tDate:" + this.getBtdate().format(form) + "\tCost:" + this.getPersonalCost() + ")";
    }

    /**
     * Method for returning Insured person`s FIO in type "Ivanov O.O."
     *
     * @param name String with FIO() of Person
     * @return String in type "Ivanov O.O."
     */
    public String outFIO(String name) {
        String[] res = name.split(" ");
        return res[0] + " " + res[1].charAt(0) + "." + res[2].charAt(0) + ".";
    }

    /**
     * Override method for correct comparison between two objects in type LocalDate (birthdays)
     *
     * @param o Object two for comparison
     * @return 0 if Objects are equal,
     * 1 if this Date > than Object`s o Date,
     * -1 if this Date < than Object`s o Date
     */
    @Override
    public int compareTo(Object o) {
        InsuredPerson ip = (InsuredPerson) o;
        if (this.getBtdate().getYear() == ip.getBtdate().getYear() &&
                this.getBtdate().getMonth() == ip.getBtdate().getMonth() &&
                this.getBtdate().getDayOfMonth() == ip.getBtdate().getDayOfMonth()
                )
            return 0;
        else if (this.getBtdate().getYear() > ip.getBtdate().getYear())
            return 1;
        else if (this.getBtdate().getYear() < ip.getBtdate().getYear())
            return -1;
        if (this.getBtdate().getYear() == ip.getBtdate().getYear() &&
                this.getBtdate().getMonth().getValue() > ip.getBtdate().getMonth().getValue())
            return 1;
        else if (this.getBtdate().getYear() == ip.getBtdate().getYear() &&
                this.getBtdate().getMonth().getValue() < ip.getBtdate().getMonth().getValue())
            return -1;


        if (this.getBtdate().getYear() == ip.getBtdate().getYear() &&
                this.getBtdate().getMonth() == ip.getBtdate().getMonth() &&
                this.getBtdate().getDayOfMonth() > ip.getBtdate().getDayOfMonth()
                )
            return 1;
        else
            return -1;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        this.flname = flname;
    }

    public double getPersonalCost() {
        return personalCost;
    }

    public void setPersonalCost(double personalCost) {
        this.personalCost = personalCost;
    }

    public LocalDate getBtdate() {

        return btdate;
    }

    public void setBtdate(LocalDate btdate) {
        this.btdate = btdate;
    }
}
