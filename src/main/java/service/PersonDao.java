package service;

import dao.ConnectionFactory;
import dao.IPersonDao;
import data.Client;
import data.InsuredPerson;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements IPersonDao {
    ConnectionFactory connectionFactory;
    private String sql="";

    private Connection createConnection() throws SQLException {
        connectionFactory =new ConnectionFactory();
        Connection connection=ConnectionFactory.getMySQLConnection();
        return connection;
    }

    @Override
    public void create(Object entity, int index) {
        InsuredPerson person=(InsuredPerson)entity;
        person.setNumber(index);
        sql="INSERT INTO insured_person(id_person, name, middle_name, surname, bth_date, insurance_cost, person_number) " +
                "values (DEFAULT,?,?,?,?,?,?)";
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement ps=c.prepareStatement(sql);) {
            ps.setString(1,person.getName());
            ps.setString(2,person.getMiddleName());
            ps.setString(3,person.getSurname());
            ps.setDate(4, Date.valueOf(person.getBtdate()));
            ps.setDouble(5,person.getPersonalCost());
            ps.setInt(6,person.getNumber());


            ps.executeUpdate();

            ResultSet rs = ps.executeQuery("SELECT last_insert_id()");
            if (rs.next()){
                person.setId(rs.getInt(1));

            } else{
                System.out.println("Error getting key");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read(long id) {


        sql="SELECT id_person, name, middle_name, surname, bth_date, insurance_cost, person_number FROM insured_person WHERE id_person=?";
        InsuredPerson person=null;
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(PreparedStatement ps=c.prepareStatement(sql);
        ) {

            ps.setLong(1,id);
            try(ResultSet rs = ps.executeQuery();) {

                while (rs.next()) {

                    int idPerson=rs.getInt("id_person");
                    String name=rs.getString("name");
                    String middleName=rs.getString("middle_name");
                    String surname=rs.getString("surname");
                    Date d = rs.getDate("bth_date");
                    LocalDate date = d.toLocalDate();
                    double cost=rs.getDouble("insurance_cost");
                    int num=rs.getInt("person_number");
                    person=new InsuredPerson (idPerson,name,middleName,surname,date,cost);
                    person.setNumber(num);

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (Object)person;
    }



    @Override
    public void update(Object entity) {
        InsuredPerson person=(InsuredPerson)entity;
        Connection c=null;
        sql="UPDATE insured_person SET name=?, middle_name=?, surname=?, bth_date=?, insurance_cost=? WHERE id_person=?";

        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement ps=c.prepareStatement(sql);) {
            ps.setString(1,person.getName());
            ps.setString(2,person.getMiddleName());
            ps.setString(3,person.getSurname());
            ps.setDate(4, Date.valueOf(person.getBtdate()));
            ps.setDouble(5,person.getPersonalCost());
            ps.setDouble(6,person.getId());


            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        sql="DELETE FROM insured_person WHERE id_person=?";
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement ps=c.prepareStatement(sql);) {

            ps.setInt(1,(int)id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList readList(long id,ArrayList list) {

        sql="SELECT id_person FROM vdata.insured_person WHERE person_number=?";


        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(PreparedStatement ps=c.prepareStatement(sql);
        ) {

            ps.setLong(1,id);
            try(ResultSet rs = ps.executeQuery();) {

                while (rs.next()) {

                    int idPerson=rs.getInt("id_person");
                    PersonService serv=new PersonService();
                    list.add(serv.getDao().read(idPerson));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }


}
