package model.dao.impl;

import model.dao.BillDao;
import model.dao.mapper.BillMapper;
import model.dao.mapper.InvoiceMapper;
import model.entity.Bill;
import model.entity.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCBillDao implements BillDao {
    private Connection connection;

    public JDBCBillDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Bill entity) throws SQLException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice(totalCost, dates, status, invoice_id) VALUES (?,?,?,?)")){
            statement.setString(1, entity.getTotalCost());
//            statement.setDate(2, entity.getDates());
            statement.setString(3,entity.getStatus());
//            statement.setInt(4,entity.getInvoiceId());

            statement.execute();
            return true;

        }catch (SQLException | RuntimeException ex){
            System.out.println("Exception" + ex.getMessage());
            throw new RuntimeException();
        }
    }
    @Override
    public Bill findById(int id) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bill WHERE id = ?")){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            final Bill bill;
            if (rs.next()) {
                bill = new BillMapper().extractFromResultSet(rs);
            } else {
                bill = null;
            }

            return bill;

        }catch (SQLException ex){
            throw new RuntimeException();
        }
    }
    @Override
    public List<Bill> findAll() {
        Map<Integer, Bill> bills = new HashMap<>();

        final String query = "" +
                " select r.id as id, r.totalCost as totalCost, r.dates as dates, r.status as status," +
                "r.invoice_id as invoice_id from bill r";// +

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            BillMapper billMapper = new BillMapper();

            while (rs.next()) {
                Bill bill = billMapper
                        .extractFromResultSet(rs);
                bill = billMapper
                        .makeUnique(bills, bill);
            }
            return new ArrayList<>(bills.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Bill bill) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE bill set totalCost=?, dates=?, status=?  where id=?")){

//            statement.setString(1, entity.getTotalCost());
//            statement.setInt(2, entity.getQuantity());
//            statement.setInt(3, entity.getId());

            statement.execute();

        }catch (SQLException | RuntimeException ex){
            throw new RuntimeException();
        }
    }
    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bill WHERE id=?")) {
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

