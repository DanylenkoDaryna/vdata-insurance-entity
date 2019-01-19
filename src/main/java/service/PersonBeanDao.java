package service;

import dao.IPersonDao;
import data.InsuredPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonBeanDao implements IPersonDao {

    final String CREATE_QUERY = "INSERT INTO insured_person(id_person, name, middle_name, surname, bth_date, insurance_cost, person_number) " +
            "values (DEFAULT,?,?,?,?,?,?)";
    final String READ_QUERY = "SELECT id_person, name, middle_name, surname, bth_date, insurance_cost, person_number FROM insured_person WHERE id_person=?";
    final String UPDATE_QUERY = "UPDATE insured_person SET name=?, middle_name=?, surname=?, bth_date=?, insurance_cost=? WHERE id_person=?";
    final String DELETE_QUERY = "DELETE FROM insured_person WHERE id_person=?";
    final String READ_LIST_QUERY = "SELECT id_person FROM vdata.insured_person WHERE person_number=?";


    private JdbcTemplate jdbc;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }


    @Override
    public void create(Object entity, int index) {
        InsuredPerson p = (InsuredPerson) entity;
        p.setNumber(index);

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_QUERY);
            ps.setString(1, p.getName());
            ps.setString(2, p.getMiddleName());
            ps.setString(3, p.getSurname());
            ps.setDate(4, Date.valueOf(p.getBtdate()));
            ps.setDouble(5, p.getPersonalCost());
            ps.setInt(6, p.getNumber());
            return ps;
        });


    }

    @Override
    public Object read(long id) {


        return jdbc.queryForObject(READ_QUERY, new Object[]{id}, InsuredPerson.class);
    }

    @Override
    public ArrayList readList(long id, ArrayList o) {

        ArrayList<InsuredPerson> res = (ArrayList<InsuredPerson>) jdbc.query(READ_LIST_QUERY,
                new Object[]{id},
                CAR_MAPPER);
        return res;


    }

    private static RowMapper CAR_MAPPER = new RowMapper() {
        public Object mapRow(ResultSet rs, int count) throws SQLException {
            InsuredPerson ip = new InsuredPerson();
            ip.setId(rs.getInt("id_person"));
            ip.setName(rs.getString("name"));
            ip.setMiddleName(rs.getString("middle_name"));
            ip.setSurname(rs.getString("surname"));
            ip.setBtdate(rs.getDate("bth_date").toLocalDate());
            ip.setPersonalCost(rs.getDouble("insurance_cost"));
            return ip;
        }
    };


    @Override
    public void update(Object entity) {

        InsuredPerson p = (InsuredPerson) entity;

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setString(1, p.getName());
            ps.setString(2, p.getMiddleName());
            ps.setString(3, p.getSurname());
            ps.setDate(4, Date.valueOf(p.getBtdate()));
            ps.setDouble(5, p.getPersonalCost());
            ps.setInt(6, p.getId());
            return ps;
        });
    }

    @Override
    public void delete(long id) {

        jdbc.queryForObject(DELETE_QUERY, new Object[]{id}, InsuredPerson.class);
    }
}
