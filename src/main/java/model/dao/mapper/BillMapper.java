package model.dao.mapper;

import model.entity.Bill;
import model.entity.Invoice;
import model.entity.enumeration.BillStatus;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BillMapper implements ObjectMapper<Bill> {
    @Override
    public Bill extractFromResultSet(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getInt("id"));
        bill.setTotalCost(rs.getInt("totalCost"));
        bill.setDates(rs.getDate("dates"));
        final String status = rs.getString("status");
        if (!StringUtil.isEmpty(status)) {
            bill.setStatus(BillStatus.valueOf(status));
        }
        bill.setUserId(rs.getInt("user_id"));

        return bill;
    }
    @Override
    public Bill makeUnique(Map<Integer, Bill> cache, Bill bill) {
        cache.putIfAbsent(bill.getId(), bill);
        return cache.get(bill.getId());
    }
}
