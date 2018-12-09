package service;

import dao.ConnectionFactory;
import dao.IClientDao;
import data.Client;
import dict.Type;

import java.sql.*;

public class ClientDao implements IClientDao {
    ConnectionFactory connectionFactory;
    private String sql="";



    private Connection createConnection() throws SQLException {
        connectionFactory =new ConnectionFactory();
        Connection connection=ConnectionFactory.getMySQLConnection();
        return connection;
    }

    @Override
    public void create(Object entity) {

        Client client=(Client)entity;
        sql="INSERT INTO client(id_client, type, name, middle_name, surname, city, street, building) " +
                "values (?,?,?,?,?,?,?,?)";
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement ps=c.prepareStatement(sql);) {
            ps.setInt(1,client.getId());
            ps.setString(2,client.getPerson().toString());
            ps.setString(3,client.getName());
            ps.setString(4,client.getMiddleName());
            ps.setString(5,client.getSurname());
            ps.setString(6,client.getCity());
            ps.setString(7,client.getStreet());
            ps.setString(8,client.getBuilding());
            System.out.println(ps.executeUpdate());

            ResultSet rs = ps.executeQuery("SELECT last_insert_id()");
            if (rs.next()){
                client.setId(rs.getInt(1));

            } else{
                System.out.println("Error getting key");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Object read(long id) {
        sql="SELECT id_client, type, name, middle_name, surname, city, street, building FROM client WHERE id_client=?";
        Client client=null;
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

                  /*  int conId = rs.getInt("id");
                    System.out.println("id = " + conId);*/
                    int idClient=rs.getInt("id_client");
                  ///////////////////////////////////////////////////////////
                    Type type;
                  if("NATURAL".equals(rs.getString("type"))) {
                     type = Type.NATURAL;
                  }else{
                      type = Type.LEGAL;
                  }
                    //////////////////////////////////////////////////////////////

                    String name=rs.getString("name");
                    String middleName=rs.getString("middle_name");
                    String surname=rs.getString("surname");
                    String city=rs.getString("city");
                    String street=rs.getString("street");
                    String building=rs.getString("building");

                  client=new Client(type,name,middleName,surname,city,street,building, idClient);


                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (Object)client;
    }

    @Override
    public void update(Object entity) {
        Client client=(Client)entity;
        sql="UPDATE client SET type=?, name=?, middle_name=?, surname=?, city=?, street=?, building=? WHERE id_client=?";
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement ps=c.prepareStatement(sql);) {
            ps.setString(1,client.getPerson().toString());
            ps.setString(2,client.getName());
            ps.setString(3,client.getMiddleName());
            ps.setString(4,client.getSurname());
            ps.setString(5,client.getCity());
            ps.setString(6,client.getStreet());
            ps.setString(7,client.getBuilding());
            ps.setInt(8,client.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {

        sql="DELETE FROM client WHERE id_client=?";
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
    public void insert(Object entity, int key) {

        Client client=(Client)entity;
        sql="INSERT INTO client(id_client, type, name, middle_name, surname, city, street, building) " +
                "values (?,?,?,?,?,?,?,?)";
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement ps=c.prepareStatement(sql);) {
            ps.setInt(1,key);
            ps.setString(2,client.getPerson().toString());
            ps.setString(3,client.getName());
            ps.setString(4,client.getMiddleName());
            ps.setString(5,client.getSurname());
            ps.setString(6,client.getCity());
            ps.setString(7,client.getStreet());
            ps.setString(8,client.getBuilding());
            System.out.println(ps.executeUpdate());

            ResultSet rs = ps.executeQuery("SELECT last_insert_id()");
            if (rs.next()){
                client.setId(rs.getInt(1));

            } else{
                System.out.println("Error getting key");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
