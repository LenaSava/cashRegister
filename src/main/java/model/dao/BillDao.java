package model.dao;

import model.entity.Bill;

import java.sql.SQLException;
import java.util.List;

public interface BillDao extends GenericDao<Bill> {

    void updateBillTotalCost(int billId, double cost);

    Bill createAndGet(Bill entity);

    Bill findOrCreate(int userId);

    void confirm(Integer id);

    void cancel(Integer id);

    List<Bill> xReport(String status);

}
