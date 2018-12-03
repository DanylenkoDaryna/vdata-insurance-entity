package dao;

import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ConnectionFactoryTest {

    @Test
    public void createConn(){
        ConnectionFactory cf=new ConnectionFactory();
        cf.init();
        try(Connection c=ConnectionFactory.getMySQLConnection();){
            PreparedStatement ps=c.prepareStatement("SELECT acceptdata FROM contract");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
// Напечатать значения в текущей строке.

             /*   int aa=rs.getInt("erd");
                System.out.println("acceptDate = " + acceptDate);*/
                        Date d= rs.getDate("acceptdata");
                LocalDate acceptDate=d.toLocalDate();
                System.out.println("acceptDate = " + acceptDate);
            }
            ps.close();
        }catch(SQLException s){
            s.printStackTrace();
        }

    }

}