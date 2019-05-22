package model.service;

import model.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    void create(Product product);

    void update(Product product);

    Optional<Product> findById(int id);

    Optional<Product> findByCode(int code);

    Optional<Product> insertIntoInvoices(int code);
}
