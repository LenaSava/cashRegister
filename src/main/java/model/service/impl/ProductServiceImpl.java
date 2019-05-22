package model.service.impl;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entity.Product;
import model.service.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(final DaoFactory daoFactory) {
        this.productDao = daoFactory.createProductDao();
    }

    @Override
    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    @Override
    public void create(final Product product) {
        if (Objects.isNull(product)) throw new IllegalArgumentException("Product must be a set");
        try {
            productDao.create(product);
        } catch (SQLException e) {
                //save error message into log
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(final Product product) {
        if (Objects.isNull(product)) throw new IllegalArgumentException("Product must be a set");
        productDao.update(product);
    }
    @Override
    public Optional<Product> findById(int id) {
        final Product product = productDao.findById(id);
        return Optional.ofNullable(product);
    }
    @Override
    public Optional<Product> findByCode(int code) {
        final Product product = productDao.findByCode(code);
        return Optional.ofNullable(product);
    }
    @Override
    public Optional<Product> insertIntoInvoices(int code) {
        final Product product = productDao.insertIntoInvoices(code);
        return Optional.ofNullable(product);
    }
}
