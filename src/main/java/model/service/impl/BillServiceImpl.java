package model.service.impl;

import model.dao.BillDao;
import model.dao.factory.DaoFactory;
import model.entity.Bill;
import model.exception.DataBaseException;
import model.exception.ServiceException;
import model.service.BillService;

import java.util.List;

/**
 * implementation of bill service
 *
 * @author Olena Savinkova
 */

public class BillServiceImpl implements BillService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private BillDao billDao = daoFactory.createBillDao();

    @Override
    public List<Bill> getAllBills() throws ServiceException {
        try {
            return billDao.findAll();
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot get All Bills");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void confirm(Integer id) throws ServiceException {
        try {
            billDao.confirm(id);
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot confirm bill");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public void cancel(Integer id) throws ServiceException {
        try {
            billDao.cancel(id);
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot cancel bill");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Bill findOrCreate(int userId) throws ServiceException {
        try {
            return billDao.findOrCreate(userId);
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot findOrCreate bill");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public List<Bill> Report(String status) throws ServiceException {
        try {
            return billDao.xReport(status);
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot create report");
            throw new ServiceException(errorMessage);
        }
    }
}
