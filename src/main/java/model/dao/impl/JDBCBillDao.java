package model.dao.impl;

import model.dao.BillDao;
import model.dao.mapper.BillMapper;
import model.dao.mapper.ObjectMapper;
import model.entity.Bill;
import model.entity.enumeration.BillStatus;
import model.exception.DataBaseException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCBillDao implements BillDao {
    private static final Logger logger = Logger.getLogger(JDBCBillDao.class);

    @Override
    public boolean create(Bill entity) throws DataBaseException {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice(totalCost, dates, status, user_id) VALUES (?,?,?,?)")){
            statement.setInt(1, entity.getTotalCost());
            statement.setDate(2, new Date(entity.getDates().getTime()));
            statement.setString(3,entity.getStatus().name());
            statement.setInt(4,entity.getUserId());

            statement.execute();
            return true;

        }catch (SQLException ex){
            logger.info("create bill failed" + ex.getMessage());
            throw new DataBaseException();
        }
    }


    @Override
    public Bill createAndGet(Bill entity) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bill(dates, status, user_id) VALUES (?,?,?)", ObjectMapper.generatedColumns)){
            statement.setDate(1, new Date(entity.getDates().getTime()));
            statement.setString(2,entity.getStatus().name());
            statement.setInt(3,entity.getUserId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                entity.setId(id);
                logger.info("createAndGet" + id);
            }
            return entity;

        }catch (SQLException ex){
            logger.info("createAndGet bill failed");
            throw new RuntimeException();
        }
    }
    @Override
    public void updateBillTotalCost(int billId, double cost) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bill set totalCost = totalCost + ? where id = ?")) {
            preparedStatement.setDouble(1, cost);
            preparedStatement.setInt(2,billId);

            preparedStatement.execute();

        }
        catch (SQLException ex) {
            logger.info("update bill failed" + ex.getMessage());
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
                bill = new Bill(null, null, Calendar.getInstance().getTime(), BillStatus.CREATE, userId);
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
    public List<Bill> findAll() throws DataBaseException {
        try (Connection connection = ConnectionPoolHolder.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bill")) {

             List<Bill> bills = new ArrayList<>();
             ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            bills.add(new BillMapper().extractFromResultSet(rs));
        }
        return bills;
    } catch (SQLException ex) {
        logger.error("cannot get all from bill", ex);
        throw new DataBaseException();
    }
}
    @Override
    public void update(Bill entity) {

    }

    public void confirm(Integer id) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE bill set status=?  where id=?")){

            statement.setString(1, BillStatus.CONFIRM.name());
            statement.setInt(2,id);


            statement.execute();

        }catch (SQLException | RuntimeException ex){
            throw new RuntimeException();
        }
    }
    public void cancel(Integer id) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE bill set status=?  where id=?")){

            statement.setString(1, BillStatus.CANCEL.name());
            statement.setInt(2,id);


            statement.execute();

        }catch (SQLException | RuntimeException ex){
            logger.info("cancel bill failed" + ex.getMessage());
            throw new RuntimeException();
        }
    }
    @Override
    public boolean delete(int id) throws DataBaseException{
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bill WHERE id=?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

            return true;
        }catch (SQLException ex){
            logger.info("delete bill failed" + ex.getMessage());
            throw new DataBaseException();
        }
    }

    public List<Bill> xReport(String status) {
        try(Connection connection = ConnectionPoolHolder.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bill WHERE status = ?")){
                preparedStatement.setString(1,status);
            List<Bill> bills = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bills.add(new BillMapper().extractFromResultSet(rs));
            }
            return bills;
        } catch (SQLException ex) {
            logger.error("cannot get report from bill", ex);
            throw new RuntimeException();
        }
    }
}

