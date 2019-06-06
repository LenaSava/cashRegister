package model.dao;

import model.exception.DataBaseException;

import java.util.List;

public interface GenericDao<T> {

    boolean create (T entity) throws DataBaseException;

    T findById(int id) throws DataBaseException;

    List<T> findAll() throws DataBaseException;

    void update(T entity) throws DataBaseException;

    boolean delete(int id) throws DataBaseException;

}
