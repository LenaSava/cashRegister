package model.dao.impl;

import model.dao.InvoiceDao;
import model.dao.mapper.InvoiceMapper;
import model.dao.mapper.ObjectMapper;
import model.entity.Bill;
import model.entity.Invoice;
import model.exception.DataBaseException;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCInvoiceDao implements InvoiceDao {
    private static final Logger logger = Logger.getLogger(JDBCInvoiceDao.class);

    @Override
    public boolean create(Invoice entity) throws DataBaseException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice(product_id, cost, quantity) VALUES (?,?,?)")){
            statement.setInt(1,entity.getProduct_id());
            statement.setDouble(2, entity.getCost());
            statement.setInt(3, entity.getQuantity());


            statement.execute();
            return true;

        }catch (SQLException ex){
            logger.info("Creation failed");
            throw new DataBaseException();
        }
    }

    @Override
    public Invoice createAndGet(Invoice entity) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice(product_id, cost, quantity, user_id, user_role_id, bill_id) VALUES (?,?,?,?,?,?)", ObjectMapper.generatedColumns)){
            statement.setInt(1,entity.getProduct_id());
            statement.setDouble(2, entity.getCost());
            statement.setInt(3, entity.getQuantity());
            statement.setInt(4,entity.getUserId());
            statement.setInt(5, entity.getUserRoleId());
            statement.setInt(6,entity.getBillId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                entity.setId(id);
            }

            return entity;

        }catch (SQLException ex){
            logger.info("createAndGet failed");
            throw new RuntimeException();
        }
    }
    @Override
    public Invoice findById(int id) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoice WHERE id = ?")){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            final Invoice invoice;
            if (rs.next()) {
                invoice = new InvoiceMapper().extractFromResultSet(rs);
            } else {
                invoice = null;
            }

            return invoice;

        }catch (SQLException ex){
            logger.info("findById failed");
            throw new RuntimeException();
        }
    }
    @Override
    public List<Invoice> findAll() {
        try (Connection connection = ConnectionPoolHolder.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoice")) {

            List<Invoice> invoices = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                invoices.add(new InvoiceMapper().extractFromResultSet(rs));
            }
            return invoices;
        } catch (SQLException ex) {
            logger.error("cannot get all from invoices", ex);
            throw new RuntimeException();
        }
    }
    @Override
    public void deleteAll() throws DataBaseException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM invoice")){
            statement.execute();

        }catch (SQLException  ex) {
            logger.info("DeleteAll failed");
            throw new DataBaseException();
        }
    }
    @Override
    public void update(Invoice entity) throws DataBaseException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE invoice set cost=?, quantity=? where id=?")){

            statement.setDouble(1, entity.getCost());
            statement.setInt(2, entity.getQuantity());
            statement.setInt(3, entity.getId());

            statement.execute();

        }catch (SQLException | RuntimeException ex){
            logger.info("update failed");
            throw new DataBaseException();
        }
    }
    @Override
    public boolean delete(int id) throws DataBaseException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM invoice WHERE id=?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

            return true;
        }catch (SQLException ex){
            logger.info("delete invoice failed");
            throw new DataBaseException();
        }
    }
}
