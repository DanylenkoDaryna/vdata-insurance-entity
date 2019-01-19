package service;

import dao.ConnectionFactory;
import dao.IContractDao;
import data.Client;
import data.InsuredPerson;
import service.Contract;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractDao<Contract>  implements IContractDao {

    private static int index=1;

    private ConnectionFactory connectionFactory;
    private Contract contract;
    private ClientService cService;
    private PersonService pService;
    private ArrayList<InsuredPerson> ar=new ArrayList<>();

    private String sql="";

    private Connection createConnection() throws SQLException {
            connectionFactory =new ConnectionFactory();
            return ConnectionFactory.getMySQLConnection();
    }



    @Override
    public void create(Object entity,ContractBuilder contractBuilder) {

            cService=new ClientService();
            pService=new PersonService();

            contract=(Contract)entity;



        sql="INSERT INTO contract(id_contract, accept_date, start_date, end_date, client_id, persons_id) " +
                "values (?,?,?,?,?,?)";
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        try(PreparedStatement ps=c.prepareStatement(sql);) {

            int clientKey=contractBuilder.getId()*2;
            int personsKey=contractBuilder.getId()*3;


            ps.setInt(1,contractBuilder.getId());
            ps.setDate(2, Date.valueOf(contractBuilder.getResult().getAcceptDate()));
            ps.setDate(3, Date.valueOf(contractBuilder.getResult().getStartDate()));
            ps.setDate(4, Date.valueOf(contractBuilder.getResult().getEndDate()));
            ps.setInt(5,clientKey);
            ps.setInt(6,personsKey);

            ps.executeUpdate();

            cService.getDao().insert(contractBuilder.getResult().getMan(),clientKey);


            for(InsuredPerson o:contractBuilder.getResult().getPersonList()){
                pService.getDao().create(o,personsKey);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void create(Object entity) {


    }

    @Override
    public Object read(long id) throws NullPointerException {


        sql = "SELECT id_contract, accept_date, start_date, end_date, client_id, persons_id FROM contract WHERE id_contract=?";
        Connection c = null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery();) {

                while (rs.next()) {
                    // Напечатать значения в текущей строке.
                    int conId = rs.getInt("id_contract");

                    Date d = rs.getDate("accept_date");
                    LocalDate acceptDate = d.toLocalDate();


                    Date a = rs.getDate("start_date");
                    LocalDate startDate = a.toLocalDate();


                    Date b = rs.getDate("end_date");
                    LocalDate endDate = b.toLocalDate();

                    int clientId = rs.getInt("client_id");

                    cService = new ClientService();
                    Client client = cService.read((long) clientId);

                    long PersonsId = rs.getInt("persons_id");


                    Director director = new Director();
                    ContractBuilder resultContract = new ContractBuilder();
                    director.constructContract(resultContract);
                    resultContract.setId(conId);
                    resultContract.setAcceptDate(acceptDate);
                    resultContract.setStartDate(startDate);
                    resultContract.setEndDate(endDate);
                    resultContract.setClient(client);

                    sql = "SELECT id_person FROM vdata.insured_person WHERE person_number=?";

                    try (PreparedStatement pss = c.prepareStatement(sql);
                    ) {

                        pss.setLong(1, PersonsId);
                        try (ResultSet rss = pss.executeQuery();) {

                            while (rss.next()) {

                                int idPerson = rss.getInt("id_person");
                                PersonService serv = new PersonService();
                                ar.add(serv.getDao().read(idPerson));
                            }

                        }
                    }

                        resultContract.setPersons(ar);


                        contract = (Contract) resultContract.getResult();
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return contract;

    }


    @Override
    public void update(Object entity,ContractBuilder contractBuilder) {
        cService=new ClientService();
        pService=new PersonService();

        contract=(Contract)entity;


        sql="UPDATE contract SET id_contract=?, accept_date=?, start_date=?, end_date=?" +
                "WHERE id_contract=?";
        Connection c=null;
        try {
            c = this.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement ps=c.prepareStatement(sql);) {

            ps.setInt(1,contractBuilder.getId());
            ps.setDate(2, Date.valueOf(contractBuilder.getResult().getAcceptDate()));
            ps.setDate(3, Date.valueOf(contractBuilder.getResult().getStartDate()));
            ps.setDate(4, Date.valueOf(contractBuilder.getResult().getEndDate()));

            ps.setInt(5,contractBuilder.getId());
            ps.execute();

            cService.getDao().update(contractBuilder.getResult().getMan());

            for(InsuredPerson o:contractBuilder.getResult().getPersonList()){
                pService.getDao().update(o);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {


        sql="DELETE FROM contract WHERE id_contract=?";
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
}
