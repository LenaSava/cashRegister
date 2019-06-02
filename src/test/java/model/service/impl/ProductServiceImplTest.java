package model.service.impl;

import model.dao.ProductDao;
import model.dao.factory.DaoFactory;
import model.dao.impl.JDBCBillDao;
import model.dao.impl.JDBCProductDao;
import model.entity.Bill;
import model.entity.Product;
import model.entity.User;
import model.entity.enumeration.BillStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product;
    private Optional<Product> optionalProduct;
    @Mock
    private JDBCProductDao productDao;
    @Mock
    private DaoFactory daoFactory;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        product = new Product(1, 100, "Rose", "Роза", 20.0, 4);
        when(daoFactory.createProductDao()).thenReturn(productDao);
        when(productDao.findById(1)).thenReturn(product);
        when(productDao.findAll()).thenReturn(Collections.singletonList(product));

    }

    @Test
    public void getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        assertEquals(product,productList.get(0));
    }

    @Test
    public void create() throws SQLException {
        Product product = new Product( 1, 101, "Rose", "Роза", 20.0, 4);
        product.setId(1);
        productService.create(product);
        verify(productDao,times(1)).create(product);
    }

    @Test
    public void update() {
        Product product = new Product( 1, 101, "Rose", "Роза", 20.0, 4);
        product.setId(1);
        productService.update(product);
        verify(productDao,times(1)).update(product);
    }

    @Test
    public void findById() {
        Optional<Product> productFind = productService.findById(1);
        assertEquals(product,productFind);
    }

    @Test
    public void findByCode() {
    }

    @Test
    public void insertIntoInvoices() {
    }

    @Test
    public void getNumberOfproducts() {
    }

    @Test
    public void findProducts() {
    }
}