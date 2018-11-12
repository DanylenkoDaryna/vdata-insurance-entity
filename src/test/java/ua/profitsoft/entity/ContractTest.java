package ua.profitsoft.entity;

import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContractTest {

    private Client first;
    private ArrayList<InsuredPerson> actual;
    private Contract c;
    private StringBuilder border;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before ContractTest.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After ContractTest.class");
    }

    @Before
    public void initTest() {
        border = new StringBuilder("---------------------------------------------------------------------------------");
        first = new Client(Type.NATURAL, "Vasilyev Vasilyi Vasilyevich", "sq. Poetry, 35");
        actual = new ArrayList<>();
        c = new Contract(86, LocalDate.of(2018, 11, 11),
                LocalDate.of(2018, 12, 11), LocalDate.of(2020, 12, 11),
                first, actual);

    }

    @After
    public void afterTest() {
        border = null;
        first = null;
        actual = null;
        c = null;

    }

    @Test
    public void totalCost() {
        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        Assert.assertEquals("good", "1151.0", String.valueOf(c.TotalCost()));
        System.out.println(border);
    }

    @Test
    public void totalCostIterator() {
        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        Assert.assertEquals("1151.0", String.valueOf(c.TotalCostI()));
        System.out.println(border);

    }


    @Test
    public void personsByDate() {

        ArrayList<InsuredPerson> expected = new ArrayList(4);

        expected.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        expected.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(2000, 1, 2), 150.50));
        expected.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(2000, 1, 6), 150.50));
        expected.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(2000, 7, 26), 1000.50));

        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(2000, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        actual.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(2000, 1, 2), 150.50));
        actual.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(2000, 7, 26), 1000.50));

        Assert.assertEquals(expected.toString(), (c.personsByDate(c.getPersonList())).toString());
        System.out.println(border);
    }

    @Test
    public void personsByName() {

        ArrayList<InsuredPerson> expected = new ArrayList(4);

        expected.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        expected.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        expected.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        expected.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        actual.add(new InsuredPerson(1, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(2, "Petrov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));
        actual.add(new InsuredPerson(3, "Petrov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(4, "Ankirov Peter Petrovich", LocalDate.of(1980, 7, 26), 1000.50));

        Assert.assertEquals(expected.toString(), (c.personsByName(c.getPersonList())).toString());
        System.out.println(border);

    }

    @Test
    public void testSearchPerson() {

        InsuredPerson expected = new InsuredPerson(1449, "Berezova Marya Ivanovna", LocalDate.of(1994, 1, 29), 1500);

        actual.add(new InsuredPerson(133, "Ivanov Ivan Ivanovich", LocalDate.of(1991, 1, 6), 150.50));
        actual.add(new InsuredPerson(1449, "Berezova Marya Ivanovna", LocalDate.of(1994, 1, 29), 1500));
        actual.add(new InsuredPerson(1448, "Ivanov Helmut Ivanovich", LocalDate.of(1991, 1, 6), 150.50));

        InsuredPerson actual = c.getPersonById(1449);
        Assert.assertEquals(expected.getFlname(), actual.getFlname());
        System.out.println(border);

        InsuredPerson actual1 = c.getPersonById(1446);
        Assert.assertEquals(null, actual1.getFlname());
        System.out.println(border);
    }

}