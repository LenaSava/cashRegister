package model.service;

import model.entity.Invoice;

import java.util.List;
import java.util.Optional;

/**
 * The root interface for invoice services
 *
 * @see model.service.impl.InvoiceServiceImpl
 */

public interface InvoiceService {

    List<Invoice> getAllInvoices();
    /**
     * method that gets all invoice's
     *
     * @return list of invoice's
     */

    void create(Invoice invoice);
    /**
     * method create invoice's
     *
     */

    void update(Invoice invoice);
    /**
     * method update invoice's
     *
     */

    boolean delete(Integer id);
    /**
     * method delete invoice's
     * @return true if delete complete
     *
     */

    Optional<Invoice> findById(int id);
    /**
     * method that gets id invoice's
     *
     * @return list of invoice's
     */

    void deleteAll();
    /**
     * method that delete all invoice's
     *
     * @return list of invoice's
     */
}
