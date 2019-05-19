package model.service;

import model.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<Invoice> getAllInvoices();

    void create(Invoice invoice);

    void update(Invoice invoice);

    boolean delete(Integer id);

    Optional<Invoice> findById(int id);
}
