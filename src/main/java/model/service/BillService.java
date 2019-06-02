package model.service;

import model.entity.Bill;

import java.util.List;

/**
 * The root interface for bill services
 *
 * @see model.service.impl.InvoiceServiceImpl
 */

public interface BillService {

    List<Bill> getAllBills();

    /**
     * method that gets all bill's
     *
     * @return list of bill's
     */
    Bill findOrCreate(int userId);

    /**
     * method find or create bill's
     *
     * @return instance of bill
     */
    void confirm(Integer id);
    /**
     * method confirm bill's by id
     *
     */

    void cancel(Integer id);
    /**
     * method cancel bill's by id
     *
     */
    List<Bill> Report(String status);
    /**
     * method create report for manager
     *
     * @return report of bill's
     */
}

