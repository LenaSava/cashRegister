package model.dao.mapper;

import model.entity.Bill;
import model.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BillMapper implements ObjectMapper<Bill> {
    @Override
    public Bill extractFromResultSet(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getInt("id"));
        bill.setTotalCost(rs.getString("totalCost"));
        bill.setDates(rs.getDate("dates"));
        bill.setStatus(rs.getString("status"));
//        bill.setInvoiceId(rs.getInt("invoice_id"));

        return bill;
    }
    @Override
    public Bill makeUnique(Map<Integer, Bill> cache, Bill bill) {
        cache.putIfAbsent(bill.getId(), bill);
        return cache.get(bill.getId());
    }
}
