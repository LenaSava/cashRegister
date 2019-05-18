package model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    boolean create (T entity) throws SQLException;
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    boolean delete(int id);
    void close();
}
