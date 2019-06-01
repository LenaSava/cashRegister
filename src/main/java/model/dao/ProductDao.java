package model.dao;

import model.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    Product findByCode(int code);
    Product insertIntoInvoices(int code);
    int getNumberOfproducts();
    List<Product> findProducts(int start, int end);
}
