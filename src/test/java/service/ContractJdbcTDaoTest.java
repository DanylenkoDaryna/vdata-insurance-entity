package service;


import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


public class ContractJdbcTDaoTest extends AbstractJUnit4SpringContextTests {


    @Test
    public void create() {


        try (AbstractApplicationContext context =
                     new FileSystemXmlApplicationContext("./src/main/resources/spring-context.xml")) {

            ContractBeanService contrService = context.getBean("contract-bean-service", ContractBeanService.class);
            Contract bean = context.getBean("contract-bean", Contract.class);
            contrService.doCreate2(bean);
        }


    }


}