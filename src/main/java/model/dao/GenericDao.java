package model.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    static final Logger logger = Logger.getLogger(GenericDao.class);
    boolean create (T entity) throws SQLException;
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    boolean delete(int id);
    void close();
}
