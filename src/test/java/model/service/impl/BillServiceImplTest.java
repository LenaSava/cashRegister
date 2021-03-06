package model.service.impl;

import model.dao.factory.DaoFactory;
import model.dao.impl.JDBCBillDao;
import model.entity.Bill;
import model.entity.enumeration.BillStatus;
import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(BillServiceImplTest.class);
    @InjectMocks
    private BillServiceImpl billService;
    private Bill bill;

    @Mock
    private JDBCBillDao billDao;
    @Mock
    private DaoFactory daoFactory;
    @Before
    public void setUp() throws Exception {
        Date date = Calendar.getInstance().getTime();
        MockitoAnnotations.initMocks(this);
        bill = new Bill(1, 100, date, BillStatus.CREATE, 1);
        when(daoFactory.createBillDao()).thenReturn(billDao);
        when(billDao.findById(1)).thenReturn(bill);
        when(billDao.findAll()).thenReturn(Collections.singletonList(bill));

    }

    @Test
    public void getAllBills() {
        List<Bill> billList = billService.getAllBills();
        assertEquals(bill,billList.get(0));
        logger.info("Test getAllBills");
    }

    @Test
    public void cancel() {
        billService.cancel(1);
        verify(billDao, times(1)).cancel(1);
    }

    @Test
    public void confirm() {
        billService.confirm(1);
        verify(billDao, times(1)).confirm(1);
    }

}