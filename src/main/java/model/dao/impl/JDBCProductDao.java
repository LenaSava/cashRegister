package model.dao.impl;

import model.dao.ProductDao;
import model.dao.mapper.ProductsMapper;
import java.sql.Connection;

public class JDBCProductDao implements ProductDao {
    private Connection connection;
    private ProductsMapper productMapper;

    public JDBCProductDao(){
        this.connection = connection;
        productMapper = new ProductsMapper();

    }
}
