//package model.service.impl;
//
//import model.dao.factory.DaoFactory;
//import model.dao.impl.JDBCInvoiceDao;
//import model.entity.Invoice;
//import model.service.InvoiceService;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//public class InvoiceServiceImplTest {
//    @InjectMocks
//    InvoiceService invoiceService;
//    List<Invoice> list;
//    Invoice invoice;
//
//    @Mock
//    JDBCInvoiceDao jdbcInvoiceImpl;
//
//
//    @Mock
//    DaoFactory daoFactory;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//
//        invoice = new Invoice();
//
//        list = new ArrayList();
//        list.add(invoice);
//        when(daoFactory.createInvoiceDao()).thenReturn(jdbcInvoiceImpl);
//
//        when(jdbcInvoiceImpl.findAll()).thenReturn(list);
////        when(jdbcInvoiceImpl.findById()).thenReturn(list);
//
//    }
//
//    @Test
//    public void getAllInvoices() {
//        List<Invoice> listGet= invoiceService.findById(id);
//        assertEquals(listGet,list);
//    }
//}