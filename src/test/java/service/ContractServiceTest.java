package service;

import data.Client;
import data.InsuredPerson;
import dict.Type;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContractServiceTest {
    private Client first;
    InsuredPerson person1;
    InsuredPerson person2;
    ArrayList<InsuredPerson> firstList = new ArrayList<>();
    ContractBuilder contractBuilder;
    Contract contract;
    ContractService cs;

    @Before
    public void initTest() {

        first = new Client(Type.NATURAL, "Vasilyev", "Vasilyi", "Vasilyevich", "Kyiv", "Poetry", "35",4);
        person1 = new InsuredPerson(1,"Aasilyi", "Aasilevich", "Aasilyev",
                LocalDate.of(2018, 9, 1), 223.5);
        person2 = new InsuredPerson(2,"Basilyi", "Bsilevich", "Basilyev",
                LocalDate.of(2018, 12, 1), 22.8);

        Director director = new Director();
        contractBuilder=new ContractBuilder();
        director.constructContract(contractBuilder);
        contractBuilder.setId(11);
        contractBuilder.setAcceptDate(LocalDate.of(2018, 9, 1));
        contractBuilder.setStartDate(LocalDate.of(2018, 10, 1));
        contractBuilder.setEndDate(LocalDate.of(2020, 10, 1));
        contractBuilder.setClient(first);
        firstList.add(person1);
        firstList.add(person2);
        contractBuilder.setPersons(firstList);
        contract =contractBuilder.getResult();


        cs=new ContractService();
    }


    @Test
    public void createContract(){

        cs.getDao().create(contract,contractBuilder);
    }

    @Test
    public void readContract(){

        Contract n=(Contract)cs.getDao().read((long)contractBuilder.getId());

        System.out.println(n.toString());
    }

    @Test
    public void updateContract(){

        contractBuilder.setEndDate(LocalDate.of(2028, 12, 31));
        cs.getDao().update(contract,contractBuilder);

    }

    @Test
    public void deleteContract(){

        cs.getDao().delete(2);

    }


    @Test
    public void springContract(){

        ApplicationContext context =
                new FileSystemXmlApplicationContext("./src/main/resources/spring-context.xml");

        Contract p = (Contract)context.getBean("contract-bean");
        System.out.println(p.toString());
    }

    @Test
    public void springContractDB() {
        try (AbstractApplicationContext context =
                     new FileSystemXmlApplicationContext("./src/main/resources/spring-context.xml")) {
            Contract bean = context.getBean("contract-bean", Contract.class);
            ContractBeanService service = context.getBean(ContractBeanService.class);
            service.doDelete(bean);
        }
    }
}