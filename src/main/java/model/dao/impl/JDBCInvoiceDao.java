package model.dao.impl;

import model.dao.InvoiceDao;
import model.dao.mapper.InvoiceMapper;
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice(cost, quantity, user_id) VALUES (?,?,?)")){
            statement.setDouble(1, entity.getCost());
            statement.setInt(2, entity.getQuantity());
            statement.setInt(3,entity.getUserId());

            statement.execute();
            return true;

        }catch (SQLException | RuntimeException ex){
            System.out.println("Exception" + ex.getMessage());
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
            throw new RuntimeException();
        }
    }
    @Override
    public List<Invoice> findAll() {
        Map<Integer, Invoice> invoices = new HashMap<>();

        final String query = "" +
                " select r.id as id, r.product_name as product_name, r.cost as cost, r.quantity as quantity," +
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
            throw new RuntimeException(e);
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
            throw new RuntimeException();
        }
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