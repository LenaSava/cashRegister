package model.service.impl;

import model.dao.BillDao;
import model.dao.DaoFactory;
import model.entity.Bill;
import model.entity.Invoice;
import model.service.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {
    private final BillDao billDao;

    public BillServiceImpl(final DaoFactory daoFactory) {
        this.billDao = daoFactory.createBillDao();
    }

    @Override
    public List<Bill> getAllBills(){
        return billDao.findAll();
    }
}
