package domin;

/**
 * Created by User on 15.02.2017.
 */
public class Customer {
    private String name;
    private int phone;
    private String  email;
    private int id;
    public Customer(){}

    public Customer(String name, int phone, String email ) {
        this.name = name;
        this.phone = phone;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
