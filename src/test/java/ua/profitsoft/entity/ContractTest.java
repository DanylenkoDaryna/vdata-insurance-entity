package ua.profitsoft.entity;

import data.Client;
import data.InsuredPerson;
import dict.Type;
import org.junit.*;
import service.Contract;
import service.ContractBuilder;
import service.Director;



import java.time.LocalDate;
import java.util.ArrayList;


public class ContractTest {

    private Client first;
    private Client seckond;
    private ArrayList<InsuredPerson> actual;
    private ArrayList<InsuredPerson> expects;
    private Contract c;
    private Contract b;


    @Before
    public void initTest() {

        first = new Client(Type.NATURAL, "Vasilyev Vasilyi Vasilyevich", "Kyiv","Poetry", "35");
        actual = new ArrayList<>();

        Director director = new Director();
        ContractBuilder contractBuilder=new ContractBuilder();

        director.constructContract(contractBuilder);


        contractBuilder.setId(93);
        contractBuilder.setAcceptDate(LocalDate.of(2018, 11, 11));
        contractBuilder.setStartDate(LocalDate.of(2018, 12, 11));
        contractBuilder.setEndDate(LocalDate.of(2020, 12, 11));
        contractBuilder.setClient(first);
        contractBuilder.setPersons(actual);

        c =contractBuilder.getResult();





        seckond = new Client(Type.NATURAL, "Vasilyev Vasilyi Vasilyevich", "Kyiv","Poetry", "35");
        expects = new ArrayList<>();

        contractBuilder.setId(93);
        contractBuilder.setAcceptDate(LocalDate.of(2018, 11, 11));
        contractBuilder.setStartDate(LocalDate.of(2018, 12, 11));
        contractBuilder.setEndDate(LocalDate.of(2020, 12, 11));
        contractBuilder.setClient(seckond);
        contractBuilder.setPersons(expects);

        b =contractBuilder.getResult();




    }

    @After
    public void afterTest() {

        first = null;
        seckond = null;
        actual = null;
        expects = null;
        c = null;
        b = null;

    }

    @Test
    public void getTotalCost() {
        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        Assert.assertEquals("good", "1151.0", String.valueOf(c.getTotalCost()));

    }




    @Test
    public void sortPersonsByDate() {

        ArrayList<InsuredPerson> expected = new ArrayList<>(4);

        expected.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        expected.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(2000, 1, 2), 150.50));
        expected.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(2000, 1, 6), 150.50));
        expected.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(2000, 7, 26), 1000.50));

        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(2000, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        actual.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(2000, 1, 2), 150.50));
        actual.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(2000, 7, 26), 1000.50));

        Assert.assertEquals(expected.toString(), (c.sortPersonsByDate(c.getPersonList())).toString());

    }

    @Test
    public void sortPersonsByName() {

        ArrayList<InsuredPerson> expected = new ArrayList<>(4);

        expected.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        expected.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        expected.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        expected.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        actual.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        Assert.assertEquals(expected.toString(), (c.sortPersonsByName(c.getPersonList())).toString());


    }

    @Test
    public void testSearchPerson() {

        InsuredPerson expected = new InsuredPerson(1449, "Berezova Marya Ivanovna", LocalDate.of(1994, 1, 29), 1500);

        actual.add(new InsuredPerson(133, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(1449, "Berezova Marya Ivanovna", LocalDate.of(1994, 1, 29), 1500));
        actual.add(new InsuredPerson(1448, "Ivanov Helmut Ivanovich", LocalDate.of(1991, 1, 6), 150.50));

        InsuredPerson actual = c.getPersonById(1449);
        Assert.assertEquals(expected.getFlname(), actual.getFlname());


        InsuredPerson actual1 = c.getPersonById(1446);
        Assert.assertEquals("Noname Noname Noname", actual1.getFlname());

    }

    @Test
    public void uploadCSV() {

        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));


        expects.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        expects.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        b.sortPersonsByName(expects);
        c.sortPersonsByName(actual);
        c.saveCSV();

        Assert.assertEquals(b.toString(), c.uploadCSV().toString());
    }

    @Test
    public void testJDBC()  {

        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        /*dao.createTable(c);
        dao.getDB().getId();
        Assert.assertEquals(86, dao.getDB().getId());*/
    }

}