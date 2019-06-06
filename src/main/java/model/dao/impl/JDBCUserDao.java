package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.ProductsMapper;
import model.dao.mapper.UserMapper;
import model.entity.Product;
import model.entity.User;
import model.exception.DataBaseException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private static final Logger logger = Logger.getLogger(JDBCUserDao.class);

    @Override
    public boolean create(User entity) throws DataBaseException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user(login,email, password, role_id) VALUES (?,?,?,?)")){

            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, 1);

            statement.execute();
            return true;



        }catch (SQLException ex){
            logger.info("Exception" + ex.getMessage());
            throw new DataBaseException();
        }

    }

    @Override
    public User findById(int id) throws DataBaseException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?")){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            User user = new UserMapper().extractFromResultSet(rs);

            return user;

        }catch (SQLException ex){
            logger.info("Exception" + ex.getMessage());
            throw new DataBaseException();
        }
    }



    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionPoolHolder.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user")) {

            List<User> users = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                users.add(new UserMapper().extractFromResultSet(rs));
            }
            return users;
        } catch (SQLException ex) {
            logger.error("cannot get all from users", ex);
            throw new RuntimeException();
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    @Override
    public User findByName(String name) throws RuntimeException {

        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login = ?")){
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            User user = new UserMapper().extractFromResultSet(rs);

            return user;

        }catch (SQLException ex){
            logger.info("Exception" + ex.getMessage());
            throw new RuntimeException();
        }
    }
}

