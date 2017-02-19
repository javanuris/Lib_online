package dao.entity;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 16.02.2017.
 */
public interface EntityDao<T> {
    T create(T t) throws SQLException;

    T findById(int id) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;

    ArrayList<T> getAll() throws SQLException;
}
