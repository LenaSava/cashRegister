package model.dao;

import model.entity.Bill;

import java.sql.SQLException;

public interface BillDao extends GenericDao<Bill> {

    Bill createAndGet(Bill entity) throws SQLException;

    Bill findOrCreate(int userId);
}
