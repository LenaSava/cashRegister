package model.dao;

import java.util.List;

public interface GenericDao<T> {

    boolean create (T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    boolean delete(int id);
}
