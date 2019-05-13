package model.dao.impl;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.dao.UserDao;

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
    public ProductDao createProductrDao() {
        return new JDBCProductDao();
    }

//    @Override
//    public RoomDao createRoomDao() {
//        return new JDBCRoomDao(getConnection());
//    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
