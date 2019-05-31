package model.dao.impl.factory;

import model.dao.*;
import model.dao.factory.DaoFactory;
import model.dao.impl.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger logger = Logger.getLogger(JDBCDaoFactory.class);

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao();
    }

    @Override
    public ProductDao createProductDao() {
        return new JDBCProductDao();
    }
    @Override
    public InvoiceDao createInvoiceDao() {
        return new JDBCInvoiceDao();
    }
    @Override
    public BillDao createBillDao() {
        return new JDBCBillDao();
    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.info("Connection failed");
            throw new RuntimeException(e);
        }
    }
}
