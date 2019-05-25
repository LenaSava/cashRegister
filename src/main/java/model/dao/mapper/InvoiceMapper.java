package model.dao.mapper;

import model.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class InvoiceMapper implements ObjectMapper<Invoice> {

    @Override
    public Invoice extractFromResultSet(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("id"));
        invoice.setProduct_id(rs.getInt("product_id"));
        invoice.setCost(rs.getDouble("cost"));
        invoice.setQuantity(rs.getInt("quantity"));
        invoice.setUserId(rs.getInt("user_id"));
        invoice.setUserRoleId(rs.getInt("user_role_id"));

        return invoice;
    }


    public Invoice makeUnique(Map<Integer, Invoice> cache, Invoice invoice) {
        cache.putIfAbsent(invoice.getId(), invoice);
        return cache.get(invoice.getId());
    }
}
