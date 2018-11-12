package ua.profitsoft.entity;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.*;

/**
 * This class is used to contain the main info about client`s Contract: identifier, Client, Insured Persons, dates
 *
 * @author Daryna
 */
public class Contract {

    private int id;
    private LocalDate acceptDate;
    private LocalDate startDate, endDate;
    private Client man;
    private ArrayList<InsuredPerson> personList;

    /**
     * internal object Comparator, used for correct work of method "personsByName" that sort list of Insured Persons
     * by alphabet
     */
    public static final Comparator FIO_COMPARATOR = new Comparator() {
        /** Override method for sorting that compare FIO Strings
         * @param o1 InsuredPerson one
         * @param o2 InsuredPerson two
         * @return int difference between Strings FIO
         */
        @Override
        public int compare(Object o1, Object o2) {
            InsuredPerson ip1 = (InsuredPerson) o1;
            InsuredPerson ip2 = (InsuredPerson) o2;
            return ip1.getFlname().compareTo(ip2.getFlname());
        }
    };


    /**
     * Constructs a new Contract with the specified info about Client, dates, list of insured persons
     *
     * @param number  identifies the Contract
     * @param accDate date of conclusion of our Contract
     * @param start   date when Contract starts to act
     * @param end     date when Contract ends to act
     * @param human   Client(type) that initiate this Contract
     * @param myList  List of insured persons(generic type InsuredPerson)
     */
    public Contract(int number, LocalDate accDate, LocalDate start, LocalDate end, Client human, ArrayList<InsuredPerson> myList) {
        this.setId(number);
        this.setAcceptDate(accDate);
        this.setStartDate(start);
        this.setEndDate(end);
        this.setMan(human);
        this.setPersonList(myList);
    }


    /**
     * Overrided method that prints the main info about object Contract
     * Return String with all the attributes of class in readable state
     *
     * @return StringBuilder
     */
    @Override
    public String toString() {

        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String border = "\n----------------------------------------------------\n";
        return border + "ContractID:\t" + this.getId() + "\nAcceptDate:\t" + this.getAcceptDate().format(form) +
                "\nStartDate:\t" + this.getStartDate().format(form) + "\nEndDate:\t" + this.getEndDate().format(form)
                + "\nClient:\t" + this.getMan().toString() + "\nPersonList:" + this.getPersonList();
    }

    /**
     * Method that counts and prints the total cost of insurance by the Contract (as the sum of all insured persons)
     * enumerates the collection and summarizes all the individual values of the insured persons
     * Implements foreach cycle
     */
    public double TotalCost() {
        double result = 0;
        for (InsuredPerson p : this.getPersonList()) {
            result += p.getPersonalCost();
        }
        System.out.println("\nTotal insurance cost:\t" + result);
        return result;
    }

    /**
     * Method that counts and prints the total cost of insurance by the Contract (as the sum of all insured persons)
     * enumerates the collection and summarizes all the individual values of the insured persons
     * Implements Iterator
     */
    public double TotalCostI() {
        double result = 0;
        ListIterator<InsuredPerson> iterator = getPersonList().listIterator();
        while (iterator.hasNext()) {
            InsuredPerson element = (InsuredPerson) iterator.next();
            result += element.getPersonalCost();
        }

        System.out.println("\nTotal insurance cost:\t" + result);
        return result;
    }

/*    public double TotalCostLam() {
        double result = 0;
        getPersonList().stream().forEach((InsuredPerson n) ->n.getPersonalCost());

        System.out.println("\nTotal insurance cost:\t" + result);
        return result;
    }*/

    /**
     * Method for sorting Insured Persons by dates of their birthday and watching in console
     * For correct comparison class InsuredPerson implements method compareTo
     *
     * @param persons ArrayList with type of objects - InsuredPerson
     * @return sorted ArrayList with type - InsuredPerson
     */
    public ArrayList<InsuredPerson> personsByDate(ArrayList<InsuredPerson> persons) {

        Collections.sort(persons);
        System.out.println(persons);
        return persons;
    }

    /**
     * Method for sorting Insured Persons by FIO`s by alphabet and watching in console
     * For correct comparison class Contract has comparator with override method compare
     *
     * @param persons ArrayList with type of objects - InsuredPerson
     * @return sorted ArrayList with type - InsuredPerson
     */
    public ArrayList<InsuredPerson> personsByName(ArrayList<InsuredPerson> persons) {

        Collections.sort(persons, FIO_COMPARATOR);
        System.out.println(persons);
        return persons;

    }

    /**
     * Method for searching Insured Person in ArrayList of persons by it`s unique id
     * use ListIterator to go through all elements
     *
     * @param i int id for search
     * @return InsuredPerson with expected id
     */
    public InsuredPerson getPersonById(int i) {
        InsuredPerson o = new InsuredPerson();

        ListIterator<InsuredPerson> iterator = getPersonList().listIterator();
        while (iterator.hasNext()) {

            InsuredPerson element = (InsuredPerson) iterator.next();
            if (element.getId() == i) {
                System.out.println(element.toString());
                return element;
            }

        }
        return o;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getMan() {
        return man;
    }

    public void setMan(Client man) {
        this.man = man;
    }

    public ArrayList<InsuredPerson> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<InsuredPerson> personList) {
        this.personList = personList;
    }


    public LocalDate getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(LocalDate acceptDate) {
        this.acceptDate = acceptDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}
