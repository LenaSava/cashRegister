package model.dao.impl;

import model.entity.Bill;
import model.service.BillService;
import model.service.ServiceFactory;
import model.service.impl.BillServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConnectionPoolHolder {
    private static ConnectionPoolHolder instance;
    private static volatile DataSource dataSource;

    private ConnectionPoolHolder(){
        getDataSource();
    }

    public static synchronized ConnectionPoolHolder getInstance(){
        if(instance == null){
            synchronized (ConnectionPoolHolder.class){
                if(instance == null){
                    instance = new ConnectionPoolHolder();
                }
            }
        }
        return instance;
    }


    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/CashRegister?characterEncoding=latin1");
                    ds.setUsername("root");
                    ds.setPassword("535715aQ");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}


