package model.dao.impl;

import model.dao.DaoFactory;
import model.dao.InvoiceDao;
import model.dao.ProductDao;
import model.dao.UserDao;
import model.entity.Invoice;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao();
    }

    @Override
    public ProductDao createProductDao() {
        return new JDBCProductDao(getConnection());
    }
    @Override
    public InvoiceDao createInvoiceDao() {
        return new JDBCInvoiceDao(getConnection());
    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
