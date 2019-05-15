package model.dao.impl;

import model.dao.ProductDao;
import model.dao.mapper.ProductsMapper;
import model.dao.mapper.UserMapper;
import model.entity.Product;
import model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCProductDao implements ProductDao {
    private Connection connection;

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product entity) throws SQLException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products(name, code, cost, quantity) VALUES (?,?,?,?)")){

            statement.setString(1, entity.getName());
            statement.setInt(2,entity.getCode());
            statement.setDouble(3, entity.getCost());
            statement.setDouble(4, entity.getQuantity());

            statement.execute();
            return true;

        }catch (SQLException | RuntimeException ex){
            throw new RuntimeException();
        }
    }
    @Override
    public Product findById(int id) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id = ?")){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            final Product product;
            if (rs.next()) {
                product = new ProductsMapper().extractFromResultSet(rs);
            } else {
                product = null;
            }

            return product;

        }catch (SQLException ex){
            throw new RuntimeException();
        }
    }
    @Override
    public List<Product> findAll() {
        Map<Integer, Product> products = new HashMap<>();
        Map<Integer, User> users = new HashMap<>();

        final String query = "" +
                " select r.id as id, r.name as name, r.code as code, r.cost as cost, r.quantity as quantity from products r";// +

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();
            ProductsMapper productMapper = new ProductsMapper();

            while (rs.next()) {
                Product product = productMapper
                        .extractFromResultSet(rs);
                /*User user = userMapper
                        .extractFromResultSet(rs);*/
                product = productMapper
                        .makeUnique(products, product);
//                user = userMapper
//                        .makeUnique(users, user);
//                room.getUsers().add(user);
            }
            return new ArrayList<>(products.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void update(Product entity) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE products set name = ?, cost=?, quantity=? where id=?")){

            statement.setString(1, entity.getName());
            statement.setInt(2,entity.getCode());
            statement.setDouble(3, entity.getCost());
            statement.setDouble(4, entity.getQuantity());

            statement.execute();

        }catch (SQLException | RuntimeException ex){
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
