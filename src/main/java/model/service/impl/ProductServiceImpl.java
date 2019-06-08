package model.service.impl;

import model.dao.factory.DaoFactory;
import model.dao.ProductDao;
import model.entity.Product;
import model.exception.DataBaseException;
import model.exception.ServiceException;
import model.service.ProductService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * implementation of product service
 *
 * @author Olena Savinkova
 */

public class ProductServiceImpl implements ProductService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ProductDao productDao = daoFactory.createProductDao();


    @Override
    public List<Product> getAllProducts(){
        try {
            return productDao.findAll();
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot getAllProducts");
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void create(final Product product) {
        if (Objects.isNull(product)) throw new IllegalArgumentException("Product must be a set");
        try {
            productDao.create(product);
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot create Products");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public void update(final Product product) {
        if (Objects.isNull(product)) throw new IllegalArgumentException("Product must be a set");
        try {
            productDao.update(product);
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot update Products");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public Optional<Product> findById(int id) {
        try {
            Product product = productDao.findById(id);
            return Optional.ofNullable(product);
        } catch (DataBaseException e) {
            String errorMessage = String.format("cannot Products findById");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public Optional<Product> findByCode(int code) {
        try {
            Product product = productDao.findByCode(code);
            return Optional.ofNullable(product);
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot Products findByCode");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public Optional<Product> insertIntoInvoices(int code) {
        try {
            Product product = productDao.insertIntoInvoices(code);
            return Optional.ofNullable(product);
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot Products findByCode");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public int getNumberOfproducts() {
        try {
            return productDao.getNumberOfproducts();
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot getNumber of products");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public List<Product> findProducts(int currentPage, int rowsPerPage){
        try {
            int start = currentPage * rowsPerPage - rowsPerPage;
            return productDao.findProducts(start, rowsPerPage);
        } catch (RuntimeException e) {
            String errorMessage = String.format("cannot Products findProducts");
            throw new ServiceException(errorMessage);
        }
    }
    @Override
    public Product getProduct(int code, String name, String name_ua, Double cost, Integer quantity) {
            Product product = new Product();

            product.setCode(code);
            product.setName(name);
            product.setName_ua(name_ua);
            product.setCost(cost);
            product.setQuantity(quantity);

            return product;
    }
}
