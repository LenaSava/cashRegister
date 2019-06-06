package model.service.impl;

import model.dao.factory.DaoFactory;
import model.dao.impl.JDBCBillDao;
import model.dao.impl.JDBCInvoiceDao;
import model.entity.Bill;
import model.entity.Invoice;
import model.entity.enumeration.BillStatus;
import model.exception.DataBaseException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InvoiceServiceImplTest {
    private static final Logger logger = Logger.getLogger(BillServiceImplTest.class);
    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    private Invoice invoice;

    @Mock
    private JDBCInvoiceDao invoiceDao;
    @Mock
    private DaoFactory daoFactory;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        invoice = new Invoice();
        when(daoFactory.createInvoiceDao()).thenReturn(invoiceDao);
        when(invoiceDao.findById(1)).thenReturn(invoice);
        when(invoiceDao.findAll()).thenReturn(Collections.singletonList(invoice));

    }

    @Test
    public void getAllInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllInvoices();
        assertEquals(invoice,invoiceList.get(0));
        logger.info("Test getAllInvoice");
    }


    @Test
    public void update() throws DataBaseException {
        Invoice invoice = new Invoice();
        invoice.setId(2);
        invoiceService.update(invoice);
        verify(invoiceDao,times(1)).update(invoice);
        logger.info("Test update");
    }

    @Test
    public void delete() throws DataBaseException {
        invoiceService.delete(2);
        verify(invoiceDao, times(1)).delete(2);
    }

}