package model.dao.impl;

import model.dao.InvoiceDao;
import model.dao.mapper.InvoiceMapper;
import model.dao.mapper.ObjectMapper;
import model.entity.Bill;
import model.entity.Invoice;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCInvoiceDao implements InvoiceDao {
    private Connection connection;

    public JDBCInvoiceDao(Connection connection){
        this.connection = connection;
    }
    @Override
    public boolean create(Invoice entity) throws SQLException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice(product_id, cost, quantity) VALUES (?,?,?)")){
            statement.setInt(1,entity.getProduct_id());
            statement.setDouble(2, entity.getCost());
            statement.setInt(3, entity.getQuantity());


            statement.execute();
            return true;

        }catch (SQLException | RuntimeException ex){
            logger.info("Creation failed");
            throw new RuntimeException();
        }
    }

    @Override
    public Invoice createAndGet(Invoice entity) throws SQLException {
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



        }catch (SQLException | RuntimeException ex){
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
        Map<Integer, Invoice> invoices = new HashMap<>();

        final String query = "" +
                " select r.id as id, r.product_id as product_id, r.cost as cost, r.quantity as quantity," +
                "r.user_id as user_id, r.user_role_id as user_role_id from invoice r";// +

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            InvoiceMapper invoiceMapper = new InvoiceMapper();

            while (rs.next()) {
                Invoice invoice = invoiceMapper
                        .extractFromResultSet(rs);
                invoice = invoiceMapper
                        .makeUnique(invoices, invoice);
            }
            return new ArrayList<>(invoices.values());
        } catch (SQLException e) {
            logger.info("findAll failed");
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteAll() {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM invoice")){
            statement.execute();

        }catch (SQLException | RuntimeException ex) {
            logger.info("DeleteAll failed");
            throw new RuntimeException();
        }
    }
    @Override
    public void update(Invoice entity) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE invoice set cost=?, quantity=? where id=?")){

            statement.setDouble(1, entity.getCost());
            statement.setInt(2, entity.getQuantity());
            statement.setInt(3, entity.getId());

            statement.execute();

        }catch (SQLException | RuntimeException ex){
            logger.info("update failed");
            throw new RuntimeException();
        }
    }
    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM invoice WHERE id=?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

            return true;
        }catch (SQLException | RuntimeException ex){
            logger.info("delete invoice failed");
            throw new RuntimeException();
        }
    }

//    @Override
//    public void close()  {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            logger.info("Connection invoice not close");
//            throw new RuntimeException(e);
//        }
//    }
}
