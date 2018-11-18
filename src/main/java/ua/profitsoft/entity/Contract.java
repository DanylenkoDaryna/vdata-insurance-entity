package ua.profitsoft.entity;

import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;


/**
 * This class is used to contain the main info about client`s Contract: identifier, Client, Insured Persons, dates
 *
 * @author Daryna
 */
public class Contract implements Serializable {

    private int id;
    private LocalDate acceptDate;
    private LocalDate startDate, endDate;
    private Client man;
    private ArrayList<InsuredPerson> personList;

    private static final String CSV_SEPARATOR = ";";
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
     * Constructs a new Contract with default parameters
     * Used for avoiding nullPointerExeption
     */
    private Contract() {
        this.setId(0);
        this.setAcceptDate(LocalDate.of(0, 1, 1));
        this.setStartDate(LocalDate.of(0, 1, 1));
        this.setEndDate(LocalDate.of(0, 1, 1));
        this.setMan(new Client());
        ArrayList<InsuredPerson> myList = new ArrayList<>();
        InsuredPerson ip = new InsuredPerson();
        myList.add(ip);
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

        if (this.equals(null))
            return "null Contract";
        else {
            DateTimeFormatter form = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String border = "\n----------------------------------------------------\n";
            return border + "ContractID:\t" + this.getId() + "\nAcceptDate:\t" + this.getAcceptDate().format(form) +
                    "\nStartDate:\t" + this.getStartDate().format(form) + "\nEndDate:\t" + this.getEndDate().format(form)
                    + "\nClient:\t" + this.getMan().toString() + "\nPersonList:" + this.getPersonList();

        }
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
        for (InsuredPerson element : getPersonList()) {
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

        for (InsuredPerson element : getPersonList()) {

            if (element.getId() == i) {
                System.out.println(element.toString());
                return element;
            }

        }
        return o;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private Client getMan() {
        return man;
    }

    private void setMan(Client man) {
        this.man = man;
    }

    public ArrayList<InsuredPerson> getPersonList() {
        return personList;
    }

    private void setPersonList(ArrayList<InsuredPerson> personList) {
        this.personList = personList;
    }


    private LocalDate getAcceptDate() {
        return acceptDate;
    }

    private void setAcceptDate(LocalDate acceptDate) {
        this.acceptDate = acceptDate;
    }

    private LocalDate getStartDate() {
        return startDate;
    }

    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    private LocalDate getEndDate() {
        return endDate;
    }

    private void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Method save the object Contract and all it`s fields: id, dates, Client, PersonList
     * object OutputStreamWriter serialize object Contract into one string with line separators
     *
     * @throws IOException
     */
    public void saveCSV() throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(".\\src\\ContractSave.csv"), "UTF-8"));

            bw.write("id;acceptDate;startDate;endDate;man;personList");
            bw.newLine();
            StringBuilder one = new StringBuilder();
            one.append(this.getId()).append(CSV_SEPARATOR).append(
                    this.getAcceptDate()).append(CSV_SEPARATOR).append(
                    this.getStartDate()).append(CSV_SEPARATOR).append(
                    this.getEndDate()).append(CSV_SEPARATOR);
            one.append(this.getMan().getPerson()).append(",");
            one.append(this.getMan().getName()).append(",");
            one.append(this.getMan().getAdress()).append(",");
            one.append(CSV_SEPARATOR);
            bw.write(one.toString());

            for (InsuredPerson p : this.getPersonList()) {
                StringBuilder oneLine = new StringBuilder();
                oneLine.append(p.getId());
                oneLine.append(",");
                oneLine.append(p.getFlname());
                oneLine.append(",");
                oneLine.append(p.getBtdate());
                oneLine.append(",");
                oneLine.append(p.getPersonalCost());
                oneLine.append("/");
                bw.write(oneLine.toString());
            }

            bw.write(System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                bw.flush();
            }
            if (bw != null) {
                bw.close();
            }
        }

    }


    /**
     * Method reads our file and split the string like the object with type Contract
     *
     * @return deserialized object with type Contract
     * @throws FileNotFoundException if file with name ContractSave.csv not found
     * @throws NullPointerException  if object could be null
     */
    public Contract uploadCSV() throws FileNotFoundException, NullPointerException {

        BufferedReader br = null;
        try {

            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(".\\src\\ContractSave.csv")));

            String line = "";
            //Read to skip the header
            br.readLine();
            //Reading from the second line
            while ((line = br.readLine()) != null) {
                String[] Details = line.split(CSV_SEPARATOR);

                if (Details.length > 0) {

                    String[] foracc = Details[1].split("-");
                    LocalDate acc = LocalDate.of(Integer.parseInt(foracc[0]), Integer.parseInt(foracc[1]), Integer.parseInt(foracc[2]));

                    String[] forstart = Details[2].split("-");
                    LocalDate start = LocalDate.of(Integer.parseInt(forstart[0]), Integer.parseInt(forstart[1]), Integer.parseInt(forstart[2]));

                    String[] forend = Details[3].split("-");
                    LocalDate end = LocalDate.of(Integer.parseInt(forend[0]), Integer.parseInt(forend[1]), Integer.parseInt(forend[2]));

                    String[] forClient = Details[4].split(",");

                    Client c = null;
                    if ("NATURAL".equals(forClient[0]))
                        c = new Client(Type.NATURAL, forClient[1], forClient[2]);
                    if (forClient[0].equals("LEGAL"))
                        c = new Client(Type.LEGAL, forClient[1], forClient[2]);

                    String[] listP = Details[5].split("/");
                    ArrayList<InsuredPerson> resList = new ArrayList<>();

                    for (String s : listP) {
                        String[] concr = s.split(",");
                        String[] date = concr[2].split("-");
                        LocalDate d = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                        double cost = Double.parseDouble(concr[3]);
                        InsuredPerson i = new InsuredPerson();
                        int id = Integer.parseInt(concr[0]);
                        i.setId(id);
                        i.setFlname(concr[1]);
                        i.setBtdate(d);
                        i.setPersonalCost(cost);
                        resList.add(i);
                    }

                    Contract res = new Contract();
                    int ident = Integer.parseInt(Details[0]);
                    res.setId(ident);
                    res.setAcceptDate(acc);
                    res.setStartDate(start);
                    res.setEndDate(end);
                    res.setMan(c);
                    res.setPersonList(resList);


                    return res;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException r) {
            r.printStackTrace();
        }
        Contract r = new Contract();
        return r;
    }


  /*  private void writeObject(java.io.ObjectOutputStream out)throws IOException {

        out.writeInt(y);
        out.defaultWriteObject(); // cохраняет поле z
    }

    private void  readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        x = in.readInt();
        y = in.readInt();
        in.defaultReadObject();
    }*/

}
