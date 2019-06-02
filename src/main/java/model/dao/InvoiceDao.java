package model.dao;

import model.entity.Invoice;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceDao extends GenericDao<Invoice> {
    Invoice createAndGet(Invoice entity);
    void deleteAll();
}
