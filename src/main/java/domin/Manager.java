package domin;

import java.sql.SQLException;

/**
 * Created by User on 16.02.2017.
 */
public interface Manager {
        Customer createAccaount(String name, int phone, String email) throws Exception;
        void changePhone(int phone) throws Exception;

}
