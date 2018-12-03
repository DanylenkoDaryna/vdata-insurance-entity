package dao;

import config.ConnectionParameters;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static BasicDataSource dataSource=null;

    public void init() {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (Exception ignored) {
            }
            dataSource = null;
        }
        try {
            Properties p = new Properties();
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:mysql://" + ConnectionParameters.DB_HOST + "/" + ConnectionParameters.DB_DATABASE);
            p.setProperty("username", ConnectionParameters.DB_USER_NAME);
            p.setProperty("password", ConnectionParameters.DB_USER_PASS);

            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getMySQLConnection() throws SQLException {
        return dataSource.getConnection();
    }


}
