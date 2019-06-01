package model.dao.impl;

import model.dao.ProductDao;
import model.dao.mapper.ProductsMapper;
import model.entity.Product;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCProductDao implements ProductDao {
    private static final Logger logger = Logger.getLogger(JDBCProductDao.class);

    @Override
    public boolean create(Product entity) throws SQLException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products(code, name, name_ua, cost, quantity) VALUES (?,?,?,?,?)")){


            statement.setInt(1,entity.getCode());
            statement.setString(2, entity.getName());
            statement.setString(3,entity.getName_ua());
            statement.setDouble(4, entity.getCost());
            statement.setInt(5, entity.getQuantity());

            statement.execute();
            return true;

        }catch (SQLException | RuntimeException ex){
            logger.info("Exception" + ex.getMessage());
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
            logger.info("Exception" + ex.getMessage());
            throw new RuntimeException();
        }
    }
    @Override
    public Product findByCode(int code) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE code = ?")){
            preparedStatement.setInt(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            final Product product;
            if (rs.next()) {
                product = new ProductsMapper().extractFromResultSet(rs);
            } else {
                product = null;
            }

            return product;

        }catch (SQLException ex){
            logger.info("Exception" + ex.getMessage());
            throw new RuntimeException();
        }
    }
    @Override
    public List<Product> findAll() {
        Map<Integer, Product> products = new HashMap<>();

        final String query = "" +
                " select r.id as id, r.code as code, r.name as name, r.name_ua as name_ua, r.cost as cost, r.quantity as quantity," +
                "r.invoice_id as invoice_id from products r";// +

        try (Connection connection = ConnectionPoolHolder.getInstance().getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            ProductsMapper productMapper = new ProductsMapper();

            while (rs.next()) {
                Product product = productMapper
                        .extractFromResultSet(rs);
                product = productMapper
                        .makeUnique(products, product);
            }
            return new ArrayList<>(products.values());
        } catch (SQLException e) {
            logger.info("Exception" + e.getMessage());
            throw new RuntimeException(e);
        }
    }



    @Override
    public void update(Product entity) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE products set code = ?, name = ?, name_ua = ?, cost=?, quantity=? where id=?")){

            statement.setInt(1,entity.getCode());
            statement.setString(2, entity.getName());
            statement.setString(3,entity.getName_ua());
            statement.setDouble(4, entity.getCost());
            statement.setInt(5, entity.getQuantity());
            statement.setInt(6, entity.getId());

            statement.execute();

        }catch (SQLException | RuntimeException ex){
            logger.info("Exception" + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean delete(int id) {
        return false;

    }

    @Override
    public Product insertIntoInvoices(int code) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO invoice SELECT * FROM products WHERE code = ?")){
            preparedStatement.setInt(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            final Product product;
            if (rs.next()) {
                product = new ProductsMapper().extractFromResultSet(rs);
                logger.info("insertIntoInvoices" + product);
            } else {
                product = null;
            }

            return product;

        }catch (SQLException ex){
            logger.info("Exception" + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public int getNumberOfproducts() {
        int numberOfRows = 0;
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(id) FROM products")){
            ResultSet rs = statement.executeQuery();

            if( rs.next()) {
                numberOfRows = rs.getInt(1);
            }

        } catch (SQLException ex){
        logger.info("Exception" + ex.getMessage());
        throw new RuntimeException();
        }
        return numberOfRows;
    }

    @Override
    public List<Product> findProducts(int start, int end) {
        List<Product> list = new ArrayList<>();
        ProductsMapper productMapper = new ProductsMapper();
        try (Connection connection = ConnectionPoolHolder.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM products LIMIT ?,?")) {
            statement.setInt(1, start);
            statement.setInt(2, end);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(productMapper.extractFromResultSet(rs));
            }

        } catch (SQLException ex) {
            logger.info("Exception" + ex.getMessage());
            throw new RuntimeException();

        }
        return list;
    }
}
