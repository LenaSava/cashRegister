package model.dao.impl;

import model.dao.BillDao;
import model.dao.mapper.BillMapper;
import model.dao.mapper.ObjectMapper;
import model.entity.Bill;
import model.entity.Invoice;
import model.entity.enumeration.BillStatus;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCBillDao implements BillDao {
    private Connection connection;

    public JDBCBillDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Bill entity) throws SQLException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice(totalCost, dates, status, user_id) VALUES (?,?,?,?)")){
            statement.setInt(1, entity.getTotalCost());
            statement.setDate(2, new Date(entity.getDates().getTime()));
            statement.setString(3,entity.getStatus().name());
            statement.setInt(4,entity.getUserId());

            statement.execute();
            return true;

        }catch (SQLException | RuntimeException ex){
            throw new RuntimeException();
        }
    }

    @Override
    public Bill createAndGet(Bill entity) throws SQLException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bill(totalCost, dates, status, user_id) VALUES (?,?,?,?)", ObjectMapper.generatedColumns)){
            statement.setInt(1, entity.getTotalCost());
            statement.setDate(2, new Date(entity.getDates().getTime()));
            statement.setString(3,entity.getStatus().name());
            statement.setInt(4,entity.getUserId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                entity.setId(id);
            }
            return entity;

        }catch (SQLException | RuntimeException ex){
            System.out.println("Exception" + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Bill findOrCreate(int userId) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bill WHERE user_id = ? and status = ?")){
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2, BillStatus.CREATE.name());
            ResultSet rs = preparedStatement.executeQuery();
            final Bill bill;
            if (rs.next()) {
                bill = new BillMapper().extractFromResultSet(rs);
            } else {
                bill = new Bill(null, 100, Calendar.getInstance().getTime(), BillStatus.CREATE, userId);
                return createAndGet(bill);
            }

            return bill;

        }catch (SQLException ex){
            System.out.println("Exception" + ex);
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
                "r.user_id as user_id from bill r";// +

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
    public void update(Bill entity) {
//        try(PreparedStatement statement = connection.prepareStatement("UPDATE bill set status=?  where id=?")){
//
//            statement.setString(1, BillStatus.CONFIRM.name());
//
//
//            statement.execute();
//
//        }catch (SQLException | RuntimeException ex){
//            throw new RuntimeException();
//        }
    }

    public void confirm(Integer id) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE bill set status=?  where id=?")){

            statement.setString(1, BillStatus.CONFIRM.name());
            statement.setInt(2,id);


            statement.execute();

        }catch (SQLException | RuntimeException ex){
            throw new RuntimeException();
        }
    }
    public void cancel(Integer id) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE bill set status=?  where id=?")){

            statement.setString(1, BillStatus.CANCEL.name());
            statement.setInt(2,id);


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

