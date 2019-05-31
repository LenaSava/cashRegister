package model.dao.factory;


import model.dao.BillDao;
import model.dao.InvoiceDao;
import model.dao.ProductDao;
import model.dao.UserDao;
import model.dao.impl.factory.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract ProductDao createProductDao();
    public abstract InvoiceDao createInvoiceDao();
    public abstract BillDao createBillDao();


    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
