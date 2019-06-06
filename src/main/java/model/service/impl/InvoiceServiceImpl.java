package model.service.impl;

import model.dao.BillDao;
import model.dao.factory.DaoFactory;
import model.dao.InvoiceDao;
import model.entity.Invoice;
import model.exception.DataBaseException;
import model.exception.ServiceException;
import model.service.InvoiceService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * implementation of invoice service
 *
 * @author Olena Savinkova
 */

public class InvoiceServiceImpl implements InvoiceService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private InvoiceDao invoiceDao = daoFactory.createInvoiceDao();
    private BillDao billDao = daoFactory.createBillDao();


    @Override
    public List<Invoice> getAllInvoices(){
        try {
            return invoiceDao.findAll();
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot getAllInvoices");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void deleteAll(){
        try {
            invoiceDao.deleteAll();
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot confirm bill");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void create(final Invoice invoice) throws ServiceException {
        try {
            invoiceDao.createAndGet(invoice);
            billDao.updateBillTotalCost(invoice.getBillId(), invoice.getCost());
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot deleteAll");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void update(final Invoice invoice) {
        if (Objects.isNull(invoice)) {
            throw new IllegalArgumentException("Invoice must be a set");
        }
        try {
            invoiceDao.update(invoice);
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot update invoice");
            throw new ServiceException(errorMessage);
        }

    }
    @Override
    public boolean delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id must be a set");
        }
        try {
            invoiceDao.delete(id);
            return true;
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot delete invoice");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Optional<Invoice> findById(int id) {
        try {
            Invoice invoice = invoiceDao.findById(id);
            return Optional.ofNullable(invoice);
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot findById");
            throw new ServiceException(errorMessage);
        }
    }
}
