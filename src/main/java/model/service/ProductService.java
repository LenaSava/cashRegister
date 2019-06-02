package model.service;

import model.entity.Product;
import java.util.List;
import java.util.Optional;

/**
 * The root interface for product services
 *
 * @see model.service.impl.ProductServiceImpl
 */

public interface ProductService {

    List<Product> getAllProducts();
    /**
     * method that gets all product's
     *
     * @return list of product's
     */

    void create(Product product);
    /**
     * method that create product
     *
     */

    void update(Product product);
    /**
     * method that create product
     *
     */

    Optional<Product> findById(int id);
    /**
     * method that product find By Id
     *
     * @return  product
     *
     */

    Optional<Product> findByCode(int code);
    /**
     * method that product find By code
     *
     * @return  product
     *
     */

    Optional<Product> insertIntoInvoices(int id);

    int getNumberOfproducts();
    /**
     * method that get Number Of products for pagination
     *
     *
     */

    List<Product> findProducts(int currentPage, int rowsPerPage);
    /**
     * method that get find Products for pagination
     *
     *
     */

}
