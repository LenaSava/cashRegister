package model.dao.mapper;

import model.entity.Product;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductsMapper implements ObjectMapper<Product> {

    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setID(rs.getInt("id"));
        product.setCode(rs.getInt("code"));
        product.setName(rs.getString("name"));
        product.setCost(rs.getDouble("cost"));
        product.setQuantity(rs.getDouble("quantity"));

        return product;
    }


    public Product makeUnique(Map<Integer, Product> cache, Product product) {
        cache.putIfAbsent(product.getID(), product);
        return cache.get(product.getID());
    }
}
