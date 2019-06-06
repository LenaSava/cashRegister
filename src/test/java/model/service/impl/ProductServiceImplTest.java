package model.service.impl;

import model.dao.factory.DaoFactory;
import model.dao.impl.JDBCProductDao;
import model.entity.Product;
import model.exception.DataBaseException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private static final Logger logger = Logger.getLogger(ProductServiceImplTest.class);
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product;
    @Mock
    private JDBCProductDao productDao;
    @Mock
    private DaoFactory daoFactory;
    @Before
    public void setUp() {
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
        logger.info("Test getProduct");
    }

    @Test
    public void create() throws DataBaseException {
        Product product = new Product( 1, 101, "Rose", "Роза", 20.0, 4);
        product.setId(1);
        productService.create(product);
        verify(productDao,times(1)).create(product);
        logger.info("Test create");
    }

    @Test
    public void update() throws DataBaseException {
        Product product = new Product( 1, 102, "Rose", "Роза", 20.0, 4);
        product.setId(2);
        productService.update(product);
        verify(productDao,times(1)).update(product);
        logger.info("Test update");
    }
}