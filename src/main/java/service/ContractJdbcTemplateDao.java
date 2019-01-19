package service;

import dao.IContractDao;
import data.InsuredPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ContractJdbcTemplateDao implements IContractDao {

    @Autowired
    private ClientBeanService cService = new ClientBeanService();
    @Autowired
    private PersonBeanService pService = new PersonBeanService();

    final String CREATE_QUERY = "INSERT INTO contract(id_contract, accept_date, start_date, end_date, client_id, persons_id) " +
            "values (?,?,?,?,?,?)";
    final String READ_QUERY = "SELECT id_contract, accept_date, start_date, end_date, client_id, persons_id FROM contract WHERE id_contract=?";
    final String UPDATE_QUERY = "UPDATE contract SET id_contract=?, accept_date=?, start_date=?, end_date=?" +
            "WHERE id_contract=?";
    final static String DELETE_QUERY = "DELETE FROM contract WHERE id_contract=?";


    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Object entity, ContractBuilder contractBuilder) {

        int clientKey = contractBuilder.getId() * 2;
        int personsKey = contractBuilder.getId() * 3;

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_QUERY);
            ps.setInt(1, contractBuilder.getId());
            ps.setDate(2, Date.valueOf(contractBuilder.getResult().getAcceptDate()));
            ps.setDate(3, Date.valueOf(contractBuilder.getResult().getStartDate()));
            ps.setDate(4, Date.valueOf(contractBuilder.getResult().getEndDate()));
            ps.setInt(5, clientKey);
            ps.setInt(6, personsKey);
            cService.doCreate(contractBuilder.getResult().getMan());
            for (InsuredPerson o : contractBuilder.getResult().getPersonList()) {
                pService.doCreate(o, personsKey);
            }
            return ps;
        });

    }

    @Override
    public void create(Object entity) {

        Contract con = (Contract) entity;
        int clientKey = con.getId() * 2;

        int personsKey = con.getId() * 3;

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_QUERY);
            ps.setInt(1, con.getId());
            ps.setDate(2, Date.valueOf(con.getAcceptDate()));
            ps.setDate(3, Date.valueOf(con.getStartDate()));
            ps.setDate(4, Date.valueOf(con.getEndDate()));
            ps.setInt(5, clientKey);
            ps.setInt(6, personsKey);

            return ps;
        });
        con.getMan().setId(clientKey);//zamena
        cService.doCreate(con.getMan());
        for (InsuredPerson o : con.getPersonList()) {
            pService.doCreate(o, personsKey);
        }


    }

    @Override
    public Object read(long id) {

        return jdbc.queryForObject(READ_QUERY, new Object[]{id}, Contract.class);

    }

    @Override
    public void update(Object entity, ContractBuilder contractBuilder) {

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setInt(1, contractBuilder.getId());
            ps.setDate(2, Date.valueOf(contractBuilder.getResult().getAcceptDate()));
            ps.setDate(3, Date.valueOf(contractBuilder.getResult().getStartDate()));
            ps.setDate(4, Date.valueOf(contractBuilder.getResult().getEndDate()));
            ps.setInt(5, contractBuilder.getId());
            return ps;
        });

        cService.doUpdate(contractBuilder.getResult().getMan());

        for (InsuredPerson o : contractBuilder.getResult().getPersonList()) {
            pService.doUpdate(o);
        }
    }

    @Override
    public void delete(long id) {
        jdbc.queryForObject(DELETE_QUERY, new Object[]{id}, Contract.class);
    }
}
