package domin;

import dao.DaoFactory;
import dao.entity.CustomerDao;

import java.sql.SQLException;

/**
 * Created by User on 16.02.2017.
 */
public class ManagerImpl implements Manager {
   private Customer customer;
    private DaoFactory daoFactory;
    private CustomerDao customerDao;

    public ManagerImpl() throws Exception {
        daoFactory = DaoFactory.getInstance();
        customerDao = daoFactory.getCustomerDao();

    }
    @Override
    public Customer createAccaount(String name, int phone, String email) throws Exception {
        return customerDao.create(new Customer(name , phone , email));
    }


    public Customer autorisation(int id) throws Exception {
        Customer tempCustomer = null;
        try {
            tempCustomer = customerDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(tempCustomer == null){
           throw  new Exception();
        }
        customer = tempCustomer;
        return tempCustomer;
    }

    public void changePhone(int phone) throws Exception {
        if(customer == null){
            throw  new Exception();
        }
        customer.setPhone(phone);
        customerDao.update(customer);
    }
}
