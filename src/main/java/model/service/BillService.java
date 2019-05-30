package model.service;

import model.entity.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
    /**
     * @return
     */
    List<Bill> getAllBills();
    Bill findOrCreate(int userId);
    void confirm(Integer id);
    void cancel(Integer id);
    List<Bill> xReport(String status);
//    List<Bill> xReport();
    List<Bill> yReport();
}

