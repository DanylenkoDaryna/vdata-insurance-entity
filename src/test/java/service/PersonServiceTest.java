package service;

import data.InsuredPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class PersonServiceTest {
    private InsuredPerson person;
    private PersonService ps;

    @Before
    public void initTest() {
        ps=new PersonService();

        person = new InsuredPerson(1,"Vasilyi", "Vasilevich", "Vasilyev", LocalDate.of(2018, 9, 1), 223.5);
    }

    @Test
    public void addPerson(){

       ps.getDao().create(person,8);// cs.getDao().create(first);
    }

    @Test
    public void outPerson(){

        System.out.println(ps.getDao().read(1).toString());
    }

    @Test
    public void updatePerson(){

        person.setName("Oscar");
       ps.getDao().update(person);
    }

    @Test
    public void deletePerson(){

       ps.getDao().delete(person.getId());
    }

    @Test
    public void springPerson(){

        LocalDate ld=LocalDate.parse("1997-01-03", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ApplicationContext context =
                new FileSystemXmlApplicationContext("./src/main/resources/spring-context.xml");

        PersonService p = (PersonService)context.getBean("person-bean");
        System.out.println(p.toString());
    }

}