package service;

import data.Client;
import dict.Type;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ClientServiceTest {
    private Client first;
    private ClientService cs;
    @Before
    public void initTest() {
       cs=new ClientService();
        first = new Client(Type.NATURAL, "Vasilyev", "Vasilyi", "Vasilyevich", "Kyiv", "Poetry", "35", 2);
    }

    @Test
    public void addClient(){

        cs.getDao().create(first);
    }

    @Test
    public void outClient(){

        System.out.println(cs.getDao().read(1).toString());
    }

    @Test
    public void updateClient(){

        first.setSurname("Ivanets");
       cs.getDao().update(first);
    }

    @Test
    public void deleteClient(){

        cs.getDao().delete(10);
    }


    @Test
    public void springAddClient(){

        ApplicationContext context =
                new FileSystemXmlApplicationContext("./src/main/resources/spring-context.xml");
        Client cl = (Client)context.getBean("client-bean");

        System.out.println(cl.toString());



    }
}