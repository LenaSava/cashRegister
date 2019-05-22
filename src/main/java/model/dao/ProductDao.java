package model.dao;

import model.entity.Product;

public interface ProductDao extends GenericDao<Product> {
    Product findByCode(int code);
    Product insertIntoInvoices(int code);
}
