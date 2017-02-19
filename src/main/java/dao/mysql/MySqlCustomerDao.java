package dao.mysql;

import dao.DaoFactory;
import dao.entity.CustomerDao;
import domin.Customer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by User on 16.02.2017.
 */
public class MySqlCustomerDao extends CustomerDao {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static final String GET_ALL_CUSTOMER = "select * from customer_r";
    public static final String UPDATE_CUSTOMER = "update customer_r set name = ? , phone = ? ,email = ? where id_customer = ?";
    private static final String INSERT_CUSTOMER = "insert into customer_r (name , phone , email) values(? ,? ,?)";
    private static final String FIND_BY_ID = "select * from customer_r where id_customer = ?";
    private static final String DELETE_FROM_TABLE= "delete from customer_r where id_customer = ?";
    public MySqlCustomerDao() throws Exception {
    }

    public Customer create(Customer customer) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER)) {
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.executeUpdate();
        }
        return customer;
    }

    public Customer findById(int id) throws SQLException {
        Customer customer = new Customer();
        try(Connection connection = daoFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setInt(1 , id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    customer = dataFromCustomerRequest(resultSet);
                }
            }
        }
        return customer;
    }

    public Customer dataFromCustomerRequest(ResultSet resultSet){
        Customer customer = new Customer();
        try {
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setPhone(resultSet.getInt(3));
            customer.setEmail(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void update(Customer customer ) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER)) {
            statement.setString(1, customer.getName());
            statement.setInt(2 , customer.getPhone());
            statement.setString(3 , customer.getEmail());
            statement.setInt(4 , customer.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Customer customer) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FROM_TABLE)) {
            statement.setInt(1 , customer.getId());
            statement.execute();
        }
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery(GET_ALL_CUSTOMER)  ) {
            while (resultSet.next()){
               customers.add(new Customer(resultSet.getString("name"),
                       resultSet.getInt("phone"),
                       resultSet.getString("email")
                       ));
            }
        }
        return customers;
    }
}
