package model.service;

import model.entity.Bill;

import java.util.List;

public interface BillService {
    /**
     * @return
     */
    List<Bill> getAllBills();
    Bill findOrCreate(int userId);
    void confirm(Integer id);
    void cancel(Integer id);
    List<Bill> Report(String status);
}

