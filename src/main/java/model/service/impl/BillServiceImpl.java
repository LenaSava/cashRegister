package model.service.impl;

import model.dao.BillDao;
import model.dao.DaoFactory;
import model.entity.Bill;
import model.service.BillService;

import java.util.List;
import java.util.Objects;

public class BillServiceImpl implements BillService {
    private final BillDao billDao;

    public BillServiceImpl(final DaoFactory daoFactory) {
        this.billDao = daoFactory.createBillDao();
    }

    @Override
    public List<Bill> getAllBills(){
        return billDao.findAll();
    }

    @Override
    public void confirm(Integer id){
        if (Objects.isNull(id)) throw new IllegalArgumentException("Invoice must be a set");
        billDao.confirm(id);
    }

    @Override
    public Bill findOrCreate(int userId){
        return billDao.findOrCreate(userId);
    }
}
