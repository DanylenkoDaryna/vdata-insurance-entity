package service;

import data.Client;
import dict.Type;
import org.junit.Before;
import org.junit.Test;

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

        //cs.getDao().delete(first.getId());
        cs.getDao().delete(10);
    }
}