package model.service;

import model.entity.Bill;
import model.entity.Invoice;

import java.util.List;

public interface BillService {
    /**
     * @return
     */
    List<Bill> getAllBills();
    Bill findOrCreate(int userId);
    void confirm(Integer id);
}
