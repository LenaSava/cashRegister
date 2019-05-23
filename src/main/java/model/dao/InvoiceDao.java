package model.dao;

import model.entity.Invoice;

import java.sql.SQLException;

public interface InvoiceDao extends GenericDao<Invoice> {
    Invoice createAndGet(Invoice entity) throws SQLException;
}
