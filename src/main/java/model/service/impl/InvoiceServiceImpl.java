package model.service.impl;

import model.dao.DaoFactory;
import model.dao.InvoiceDao;
import model.entity.Invoice;
import model.service.InvoiceService;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceDao invoiceDao;

    public InvoiceServiceImpl(final DaoFactory daoFactory) {
        this.invoiceDao = daoFactory.createInvoiceDao();
    }

    @Override
    public List<Invoice> getAllInvoices(){
        return invoiceDao.findAll();
    }

    @Override
    public void create(final Invoice invoice) {
        if (Objects.isNull(invoice)) throw new IllegalArgumentException("Invoice must be a set");
        try {
            invoiceDao.create(invoice);
        } catch (SQLException e) {
            //save error message into log
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(final Invoice invoice) {
        if (Objects.isNull(invoice)) throw new IllegalArgumentException("Invoice must be a set");
        invoiceDao.update(invoice);
    }
    @Override
    public boolean delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id must be a set");
        }
        invoiceDao.delete(id);
        return true;
    }

    @Override
    public Optional<Invoice> findById(int id) {
        final Invoice invoice = invoiceDao.findById(id);
        return Optional.ofNullable(invoice);
    }

}
