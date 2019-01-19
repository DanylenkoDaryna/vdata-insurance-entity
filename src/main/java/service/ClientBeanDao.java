package service;

import data.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import dao.IClientDao;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class ClientBeanDao implements IClientDao {

    final String CREATE_QUERY = "INSERT INTO client(id_client, type, name, middle_name, surname, city, street, building) " +
            "values (?,?,?,?,?,?,?,?)";
    final String READ_QUERY = "SELECT id_client, type, name, middle_name, surname, city, street, building FROM client WHERE id_client=?";
    final String UPDATE_QUERY = "UPDATE client SET type=?, name=?, middle_name=?, surname=?, city=?, street=?, building=? WHERE id_client=?";
    final String DELETE_QUERY = "DELETE FROM client WHERE id_client=?";


    private JdbcTemplate jdbc;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }


    @Override
    public void create(Object entity) {
        Client c = (Client) entity;
        // KeyHolder key = new GeneratedKeyHolder();


        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_QUERY);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getPerson().toString());
            ps.setString(3, c.getName());
            ps.setString(4, c.getMiddleName());
            ps.setString(5, c.getSurname());
            ps.setString(6, c.getCity());
            ps.setString(7, c.getStreet());
            ps.setString(8, c.getBuilding());
            return ps;
        });

    }

    @Override
    public Object read(long id) {

        return (Object) jdbc.queryForObject(READ_QUERY, new Object[]{id}, Client.class);
    }

    @Override
    public void update(Object entity) {
        Client c = (Client) entity;
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);

            ps.setString(1, c.getPerson().toString());
            ps.setString(2, c.getName());
            ps.setString(3, c.getMiddleName());
            ps.setString(4, c.getSurname());
            ps.setString(5, c.getCity());
            ps.setString(6, c.getStreet());
            ps.setString(7, c.getBuilding());
            ps.setInt(8, c.getId());
            return ps;
        });


    }

    @Override
    public void delete(long id) {

        jdbc.queryForObject(DELETE_QUERY, new Object[]{id}, Client.class);
    }

    @Override
    public void insert(Object man, int id) {

    }
}
