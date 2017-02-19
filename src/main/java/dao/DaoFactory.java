package dao;

import dao.entity.CustomerDao;
import dao.mysql.MySqlCustomerDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by User on 16.02.2017.
 */
public class DaoFactory {
    private static DaoFactory daoFactory;
    private static String type;
    private String user;
    private String password;
    private String url;
    private String driver;


    private DaoFactory() throws Exception {
        loadProperties();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    public static DaoFactory getInstance() throws Exception {
        if (null == daoFactory) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public Connection getConnection() throws SQLException {
     return DriverManager.getConnection(url , user , password);
    }

    public CustomerDao getCustomerDao() throws Exception {
        if(type.equalsIgnoreCase("mysql")){
            return new MySqlCustomerDao();
        }else{
            return new MySqlCustomerDao();
        }
    }

    private void loadProperties() throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(DaoFactory.class.getResourceAsStream("/db.properties"));
            type = properties.getProperty("type");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
