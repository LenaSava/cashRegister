package model.dao.impl;

import model.dao.BillDao;
import model.dao.mapper.BillMapper;
import model.dao.mapper.ObjectMapper;
import model.entity.Bill;
import model.entity.Invoice;
import model.entity.enumeration.BillStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCBillDao implements BillDao {
    private static final Logger logger = Logger.getLogger(JDBCBillDao.class);
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
            logger.info("create bill failed" + ex.getMessage());
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
                logger.info("createAndGet" + id);
            }
            return entity;

        }catch (SQLException | RuntimeException ex){
            logger.info("createAndGet bill failed");
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
            logger.info("findOrCreate bill failed");
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
            logger.info("findById bill failed");
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
            logger.info("findById bill failed");
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Bill entity) {

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
            logger.info("cancel bill failed" + ex.getMessage());
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
            logger.info("delete bill failed" + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.info("close bill failed" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

//    public Bill xReport(String status) {
//        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bill WHERE status = ?")){
//            preparedStatement.setString(1,status);
//            ResultSet rs = preparedStatement.executeQuery();
//            final Bill bill;
//            if (rs.next()) {
//                bill = new BillMapper().extractFromResultSet(rs);
//            } else {
//                bill = null;
//            }
//
//            return bill;
//
//        }catch (SQLException ex){
//            logger.info("findById bill failed");
//            throw new RuntimeException();
//        }
//
//    }
//public List<Bill> xReport(String status) {
//    Map<Integer, Bill> bills = new HashMap<>();
//
//    final String query = "" +
//            "SELECT r.id as id, r.totalCost as totalCost, r.dates as dates, r.status as status," +
//            "r.user_id as user_id from bill r WHERE r.status=status";
//
//    try (Statement st = connection.createStatement()) {
//        ResultSet rs = st.executeQuery(query);
//
//        BillMapper billMapper = new BillMapper();
//
//        while (rs.next()) {
//            Bill bill = billMapper
//                    .extractFromResultSet(rs);
//            bill = billMapper
//                    .makeUnique(bills, bill);
//        }
//        return new ArrayList<>(bills.values());
//    } catch (SQLException e) {
//        logger.info("findById bill failed" + e);
//        throw new RuntimeException(e);
//    }
//}
public List<Bill> xReport(String status) {
    Map<Integer, Bill> bills = new HashMap<>();
    try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bill WHERE status = ?")){
            preparedStatement.setString(1,status);
            ResultSet rs = preparedStatement.executeQuery();
            final Bill bill;
            if (rs.next()) {
                bill = new BillMapper().extractFromResultSet(rs);
            } else {
                bill = null;
            }

            return new ArrayList<>(bills.values());

        }catch (SQLException ex){
            logger.info("findById bill failed" + ex);
            throw new RuntimeException();
        }

    }


    @Override
    public List<Bill> yReport() {
        return null;
    }
}

