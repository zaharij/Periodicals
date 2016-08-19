/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import model.connection.DataAccessFactory;
import model.connection.JDBCUtils;
import static model.dao.impl.constants.ConstantsDaoImpl.*;
import model.service.constants.ConfigLog;
import model.service.constants.LogSettings;

/**
 * AdminDao
 * @author Zakhar
 */
public class AdminDao {
    public final static String GET_ADMIN_BY_LOGIN_SQL = "SELECT * FROM superadmin WHERE login = ?";
    public final static int LOGIN_SQL_GET_ADMIN_BY_LOGIN_NUMBER = 1;
    
    public final static String SET_ADMIN_RIGHTS_SQL = "UPDATE user SET is_admin = ? WHERE email = ?";
    public final static int IS_ADMIN_SET_ADMIN_RIGHTS_SQL_NUMBER = 1;
    public final static int EMAIL_SET_ADMIN_RIGHTS_SQL_NUMBER = 2;
    
    public final static String SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL = "UPDATE followers SET nextdate = ? WHERE email = ? AND periodical = ?";
    public final static int NEXTDATE_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER = 1;
    public final static int EMAIL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER = 2;
    public final static int PERIODICAL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER = 3;
    
    public final static String GET_FOLLOWERS_ALL_SQL = "SELECT * FROM followers";
    
    public final static String SET_MONEY_TO_FOLLOWERS_SQL = "UPDATE followers SET money = ? WHERE email = ? AND periodical = ?";
    public final static int MONEY_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER = 1;
    public final static int EMAIL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER = 2;
    public final static int PERIODICAL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER = 3;
    
    public final static String GET_SUBSCRIPED_PERIODICAL_SQL = "SELECT * FROM followers WHERE email = ? AND periodical = ?";
    public final static int EMAIL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER = 1;
    public final static int PERIODICAL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER = 2;
    
    public final static String DELETE_FOLLOWERS_BY_END_DATE_SQL = "DELETE FROM followers WHERE date < ?";
    public final static int DATE_DELETE_FOLLOWERS_BY_END_DATE_SQL_NUMBER = 1;

    private LogSettings logSettings = new LogSettings();
    private ConfigLog configLog = new ConfigLog(logSettings.LOG_PROPERTIES_FILE);
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserDao.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final JDBCUtils jdbcUtils = DataAccessFactory.getJDBCUtils();
    
    /**
     * getSuperAdminPassword
     * <p>
     * returns administrator's password as a hash code
     * </p>
     * @param login - administrator's identifier
     * @return hash code of the password
     */
    public int getSuperAdminPassword(String login){
        int value = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ADMIN_BY_LOGIN_SQL);
            preparedStatement.setString(LOGIN_SQL_GET_ADMIN_BY_LOGIN_NUMBER, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                value = resultSet.getInt(ADMIN_PASSWORD_COLUMN_NUMBER);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return value;
    }
    
    /**
     * setAdminRights
     * <p>
     * sets administrator rights to current user
     * </p>
     * @param email - user's identifier
     */
    public void setAdminRights (String email, int rights){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(SET_ADMIN_RIGHTS_SQL);
            preparedStatement.setInt(IS_ADMIN_SET_ADMIN_RIGHTS_SQL_NUMBER, IS_ADMIN);
            preparedStatement.setInt(IS_ADMIN_SET_ADMIN_RIGHTS_SQL_NUMBER, rights);
            preparedStatement.setString(EMAIL_SET_ADMIN_RIGHTS_SQL_NUMBER, email);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * updateDateFreezed
     * <p>
     * updates date of freezing money
     * </p>
     * @param email - user's identifier
     * @param periodical - periodical identifier
     * @param newDate new date to set 
     */
    public void updateDateFreezed (String email, String periodical, Timestamp newDate){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL);
            preparedStatement.setTimestamp(NEXTDATE_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER, newDate);
            preparedStatement.setString(EMAIL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER, email);
            preparedStatement.setString(PERIODICAL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER, periodical);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * updateFreezed
     * <p>
     * updates "freezed" parameters - money and date
     * </p>
     * @param dateCurrent - current date
     */
    public void updateFreezed(Timestamp dateCurrent){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_FOLLOWERS_ALL_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getTimestamp(FOLLOWERS_DATE_FREEZ_COLUMN_NAME).before(dateCurrent)){
                    updateDateFreezed(resultSet.getString(FOLLOWERS_EMAIL_COLUMN_NAME)
                            , resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME)
                            , resultSet.getTimestamp(FOLLOWERS_DATE_FREEZ_COLUMN_NAME));
                    updateMounthFreezMoney(resultSet.getString(FOLLOWERS_EMAIL_COLUMN_NAME)
                            , resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME));
                    
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * updateMounthFreezMoney
     * <p>
     * updates user's money, payed for the periodical
     * </p>
     * @param email - user's identifier
     * @param periodical - periodical identifier
     */
    public void updateMounthFreezMoney(String email, String periodical){
        double currentAllPayedMoney = getPayedMoneyForPeriodical (email, periodical);
        double monthPrice = getMonthPriceForPeriodical (email, periodical);
        double newMoney;
        if (currentAllPayedMoney >= monthPrice){
            newMoney = currentAllPayedMoney - monthPrice;
            try (Connection connection = jdbcUtils.getConnection();){
                preparedStatement = connection.prepareStatement(SET_MONEY_TO_FOLLOWERS_SQL);
                preparedStatement.setDouble(MONEY_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER, newMoney);
                preparedStatement.setString(EMAIL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER, email);
                preparedStatement.setString(PERIODICAL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER, periodical);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                configLog.init();
                logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
                Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException();
            }   
        }
    }
    
    /**
     * getMonthPriceForPeriodical
     * <p>
     * returns user's month price for periodical
     * </p>
     * @param email - user's identifier
     * return money (double)
     */
    public double getMonthPriceForPeriodical (String email, String periodical){
        double result = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_SUBSCRIPED_PERIODICAL_SQL);
            preparedStatement.setString(EMAIL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, email);
            preparedStatement.setString(PERIODICAL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getDouble(FOLLOWERS_MOUNTHPRICE_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return result;
    }
    
    /**
     * getPayedMoneyForPeriodical
     * <p>
     * returns all payed money for periodical
     * </p>
     * @param email - user's identifier
     * return money (double)
     */
    public double getPayedMoneyForPeriodical (String email, String periodical){
        double result = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_SUBSCRIPED_PERIODICAL_SQL);
            preparedStatement.setString(EMAIL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, email);
            preparedStatement.setString(PERIODICAL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getDouble(FOLLOWERS_MONEY_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return result;
    }
       
    /**
     * getFreezedListByDate
     * <p>
     * returns hash map with periodicals names and moneys, which date was ended
     * </p>
     * @param dateCurrent - current date (Timestamp)
     * @return hash map with periodicals names and moneys
     */
    public HashMap<String, Double> getFreezedListByDate(Timestamp dateCurrent){
        HashMap<String, Double> freezedMap = new HashMap<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_FOLLOWERS_ALL_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getTimestamp(FOLLOWERS_DATE_FREEZ_COLUMN_NAME).before(dateCurrent)){
                    if (freezedMap.containsKey(resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME))){
                        String keyPeriodical = resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME);
                        double existMoney = freezedMap.get(keyPeriodical);
                        double putMoney = existMoney + resultSet.getDouble(FOLLOWERS_MOUNTHPRICE_COLUMN_NAME);
                        freezedMap.put(keyPeriodical, putMoney);
                    } else {
                        freezedMap.put(resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME)
                                , resultSet.getDouble(FOLLOWERS_MOUNTHPRICE_COLUMN_NAME));
                    }                   
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return freezedMap;
    }
    
    /**
     * deleteFreezed
     * <p>
     * deletes followers which date was ended
     * </p>
     * @param dateCurrent - current date (Timestamp)
     */
    public void deleteFreezed (Timestamp dateCurrent){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(DELETE_FOLLOWERS_BY_END_DATE_SQL);
            preparedStatement.setTimestamp(DATE_DELETE_FOLLOWERS_BY_END_DATE_SQL_NUMBER, dateCurrent);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + AdminDao.class.getName());
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
}
