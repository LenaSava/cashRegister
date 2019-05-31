package model.service.impl;

import model.dao.factory.DaoFactory;
import model.dao.impl.JDBCBillDao;
import model.entity.Bill;
import model.entity.enumeration.BillStatus;
import model.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.assertEquals;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class BillServiceImplTest {
    @InjectMocks
    private Bill bill;
    @Mock
    private ServiceFactory serviceFactory;
    @Mock
    private BillServiceImpl billService;
    @Mock
    private JDBCBillDao billDao;
    @Mock
    private DaoFactory daoFactory;
    @Before
    public void setUp() throws Exception {
        Date date = Calendar.getInstance().getTime();
        MockitoAnnotations.initMocks(this);
        bill = new Bill(1, 100, date, BillStatus.CREATE, 1);
        when(billDao.findById(1)).thenReturn(bill);
        when(billDao.findAll()).thenReturn(Collections.singletonList(bill));
        when(daoFactory.createBillDao()).thenReturn(billDao);
//        BillService billService = ServiceFactory.getBillService();
        when(serviceFactory.getBillService()).thenReturn(billService);


    }

    @Test
    public void getAllBills() {
        List<Bill> billList = billService.getAllBills();
        billList.stream().forEach(System.out::println);
        assertEquals(bill,billList.get(0));
    }

    @Test
    public void confirm() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void findOrCreate() {
    }

    @Test
    public void Report() {
    }

    @Test
    public void yReport() {
    }
}