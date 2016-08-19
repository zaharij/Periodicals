/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.connection.DataAccessFactory;
import model.connection.JDBCUtils;
import model.entities.User;
import model.service.constants.ConfigLog;
import model.service.constants.LogSettings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.dao.impl.constants.ConstantsDaoImpl.*;

/**
 * UserDao
 * User's service data access object
 * puts or gets values from the data base
 * @author Zakhar
 */
public class UserDao {
    public final static String INSERT_INTO_USER_SQL = "INSERT INTO user (email, password, first_name, last_name, middle_name, account) VALUES(?,?,?,?,?,?)";
    public final static int EMAIL_SQL_NUMBER = 1;
    public final static int PASSWORD_SQL_NUMBER = 2;
    public final static int FIRST_NAME_SQL_NUMBER = 3;
    public final static int LAST_NAME_SQL_NUMBER = 4;
    public final static int MIDDLE_NAME_SQL_NUMBER = 5;
    public final static int ACCOUNT_SQL_NUMBER = 6;   
    
    public final static String INSERT_INTO_PERIODICALS_USER_SQL = "INSERT INTO followers (email, periodical, money, date, monthprice, nextdate) VALUES (?,?,?,?,?,?)";
    public final static int EMAIL_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS = 1;
    public final static int PERIODICAL_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS = 2;
    public final static int MONEY_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS = 3;
    public final static int DATE_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS = 4;
    public final static int MONTHPRICE_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS = 5;
    public final static int NEXT_DATE_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS = 6;//next salaries for authors
    
    public final static String GET_USER_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?";
    public final static int EMAIL_SQL_NUMBER_GET_USER_BY_EMAIL = 1;
    
    public final static String REPLENISH_ACCOUNT_SQL = "UPDATE user SET account = ? WHERE email = ?";
    public final static int ACCOUNT_SQL_REPLENISH_ACCOUNT = 1;
    public final static int EMAIL_SQL_REPLENISH_ACCOUNT = 2;
    
    public final static String GET_IMG_VALUE_SQL = "SELECT * FROM logimg WHERE id = ?";
    public final static int ID_IMG_VALUE_SQL_NUMBER = 1;
    
    public final static String GET_ALL_USERS_SQL = "SELECT * FROM user WHERE is_author != 0";
    
    public final static String GET_ALL_REGISTERED_USERS_SQL = "SELECT * FROM user";
    
    public final static String UPDATE_PERMITTED_NUMBER_SQL = "UPDATE user SET permitted = ? WHERE email = ?";
    public final static int PERMITTED_PERMITTED_NUMBER_SQL_NUMBER = 1;
    public final static int EMAIL_PERMITTED_NUMBER_SQL_NUMBER = 2;
    
    public final static String DELETE_USER_SQL = "DELETE FROM user WHERE email = ?";
    public final static int EMAIL_DELETE_USER_SQL_NUMBER = 1;
    
    public final static String SET_BLOCK_USER_SQL = "UPDATE user SET block = ? WHERE email = ?";
    public final static int BLOCK_SET_BLOCK_USER_SQL_NUMBER = 1;
    public final static int EMAIL_SET_BLOCK_USER_SQL_NUMBER = 2;
    
    public final static String SET_AUTHOR_RIGHTS_SQL = "UPDATE user SET is_author = ? WHERE email = ?";
    public final static int IS_AUTHOR_SET_AUTHOR_RIGHTS_SQL_NUMBER = 1;
    public final static int EMAIL_SET_AUTHOR_RIGHTS_SQL_NUMBER = 2;   
    
    private LogSettings logSettings = new LogSettings();
    private ConfigLog configLog = new ConfigLog(logSettings.LOG_PROPERTIES_FILE);
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserDao.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final JDBCUtils jdbcUtils = DataAccessFactory.getJDBCUtils();
    
    /**
     * insertUserIntoDb
     * <p>
     * inserts new user into the data base, using User object
     * </p>
     * @param user object User contains all needed information about the new user
     */
    public void insertUserIntoDb(User user){
        try (Connection connection = jdbcUtils.getConnection();){            
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(INSERT_INTO_USER_SQL);
                preparedStatement.setString(EMAIL_SQL_NUMBER, user.getEmail());
                preparedStatement.setInt(PASSWORD_SQL_NUMBER, user.getPassword());
                preparedStatement.setString(FIRST_NAME_SQL_NUMBER, user.getFirstName());
                preparedStatement.setString(LAST_NAME_SQL_NUMBER, user.getLastName());
                preparedStatement.setString(MIDDLE_NAME_SQL_NUMBER, user.getMiddleName());              
                preparedStatement.setDouble(ACCOUNT_SQL_NUMBER, DEFAULT_ACCOUNT_MONEY);
                preparedStatement.execute();
                connection.commit();
            }catch (SQLException ex) {
                connection.rollback();
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);                
                configLog.init();
                logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
                throw new RuntimeException();
            }
        } catch (SQLException ex) {           
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            throw new RuntimeException();
        }
    }
    
    /**
     * insertUserToPeriodicalsTableIntoDb
     * <p>
     * insert user into the "periodicals" table
     * , that means that current user is signed up the current periodical
     * , first checking user's account
     * </p>
     * @param email - user's email, to identify current user
     * @param periodical - periodical title or subject, to identify current periodical
     * @param date - date expiration
     * @param money - payed money
     * @param mounthPrice - price for the month
     */
    public void insertUserToPeriodicalsTableIntoDb(String email, String periodical, Timestamp date, double money, double mounthPrice){
        double userMoney = getUserMoney(email);
        if (userMoney >= money){
            userMoney -= money;
        } else {
            throw new RuntimeException();
        }
        try (Connection connection = jdbcUtils.getConnection();){           
            try {
                connection.setAutoCommit(false);
                setMoneyToAccount (email, userMoney);
                double moneyRest = money - mounthPrice;
                Timestamp dateFreez = new Timestamp(System.currentTimeMillis());
                dateFreez.setMonth(dateFreez.getMonth() + FREEZE_MONEY_DEFAULT_PERIOD_IN_MONTHES);
                preparedStatement = connection.prepareStatement(INSERT_INTO_PERIODICALS_USER_SQL);
                preparedStatement.setString(EMAIL_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS, email);
                preparedStatement.setString(PERIODICAL_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS, periodical);
                preparedStatement.setDouble(MONEY_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS, moneyRest);
                preparedStatement.setTimestamp(DATE_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS, date);
                preparedStatement.setDouble(MONTHPRICE_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS, mounthPrice);
                preparedStatement.setTimestamp(NEXT_DATE_SQL_NUMBER_INSERT_USER_INTO_PERIODICALS, dateFreez);
                preparedStatement.execute();
                connection.commit();
            }catch (SQLException ex) {
                connection.rollback();
                configLog.init();
                logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException();
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * getUserMoney
     * <p>
     * returns all money user has
     * </p>
     * @param email - user's email, to identify current user
     * return all money (double)
     */
    public double getUserMoney (String email){
        double result = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(EMAIL_SQL_NUMBER_GET_USER_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();           
            while (resultSet.next()) {
                result = resultSet.getDouble(USER_ACCOUNT_COLUMN_NAME);
            }
        } catch (SQLException ex) {   
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return result;
    }
    
    /**
     * setMoneyToAccount
     * <p>
     * updates user;s account, and returns the result
     * </p>
     * @param email - user's email, to identify current user
     */
    public void setMoneyToAccount (String email, double money){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(REPLENISH_ACCOUNT_SQL);
            preparedStatement.setDouble(ACCOUNT_SQL_REPLENISH_ACCOUNT, money);
            preparedStatement.setString(EMAIL_SQL_REPLENISH_ACCOUNT, email);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * getLoginImgValue
     * <p>
     * returns the value of the register picture by its id
     * </p>
     * @param id of the current picture
     * @return value
     */
    public String getLoginImgValue(int id){
        String value = null;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_IMG_VALUE_SQL);
            preparedStatement.setInt(ID_IMG_VALUE_SQL_NUMBER, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                value = resultSet.getString(LOG_IMG_VALUE_COLUMN_NUMBER);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return value;
    }    
    
    /**
     * getUserPassword
     * <p>
     * returns user's password, using his email to identify
     * </p>
     * @param email - user's identifier
     * @return hash code of user's password
     */
    public int getUserPassword(String email){
        int value = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(EMAIL_SQL_NUMBER_GET_USER_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                value = resultSet.getInt(USER_PASSWORD_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return value;
    }
    
    /**
     * getWriterRights
     * <p>
     * checks if user has rights as a writer
     * , returns 1 if user is a writer, and 0 if is not
     * </p>
     * @param email - user's identifier
     * @return result (0/1)
     */
    public int getWriterRights (String email){
        int result = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(EMAIL_SQL_NUMBER_GET_USER_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(USER_IS_AUTHOR_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return result;
    }
    
    /**
     * getUserFirstName
     * <p>
     * returns user's first name, using email as an identifier
     * </p>
     * @param email - user's identifier
     * @return user's first name
     */
    public String getUserFirstName (String email){
        String firstName = null;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(EMAIL_SQL_NUMBER_GET_USER_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                firstName = resultSet.getString(USER_FIRST_NAME_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return firstName;
    }
    
     /**
     * getUserLastName
     * <p>
     * returns user's last name, using email as an identifier
     * </p>
     * @param email - user's identifier
     * @return user's last name
     */
    public String getUserLastName (String email){
        String lastName = null;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(EMAIL_SQL_NUMBER_GET_USER_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lastName = resultSet.getString(USER_LAST_NAME_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return lastName;
    }
    
    /**
     * getAdminRights
     * <p>
     * checks if user has rights as an administrator
     * , using email as an identifier
     * </p>
     * @param email - user's identifier
     * @return result (1) - if user has rights as an administrator, (0) if user is not an administrator 
     */
    public int getAdminRights (String email){
        int result = RESULT_SQL_EXCEPTION;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(EMAIL_SQL_NUMBER_GET_USER_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(USER_IS_ADMIN_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return result;
    }
    
    /**
     * getAuthorsNumber
     * returns number of authors
     * @return result
     */
    public int getAuthorsNumber (){
        int authorsNumCount = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(USER_IS_AUTHOR_COLUMN_NAME) != IS_AUTHOR_DEFAULT){
                    authorsNumCount++;
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return authorsNumCount;
    }
    
    /**
     * getBlockedUsers
     * returns all blocked users
     * @return result
     */
    public List<String> getBlockedUsers (){
        List<String> resultList = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_REGISTERED_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(USER_BLOCK_COLUMN_NAME) == IS_BLOCKED){
                    resultList.add(resultSet.getString(USER_EMAIL_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return resultList;
    }
    
    /**
     * getUnblockedUsers
     * returns all unblocked users
     * @return result
     */
    public List<String> getUnblockedUsers (){
        List<String> resultList = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_REGISTERED_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(USER_BLOCK_COLUMN_NAME) == IS_BLOCKED_DEFAULT){
                    resultList.add(resultSet.getString(USER_EMAIL_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return resultList;
    }
    
    /**
     * getUserPermittedNumber
     * returns the number of permitted articles
     * @param email - user's email
     * return number of permitted articles
     */
    public int getUserPermittedNumber (String email){
        int result = DEFAULT_VALUE;
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(USER_PERMITTED_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return result;
    }
    
    /**
     * updatePermittedNumber
     * updates permitted articles number
     * @param email - user's identifier
     * @param permittedNumber - new permitted number
     */
    public void updatePermittedNumber (String email, int permittedNumber){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(UPDATE_PERMITTED_NUMBER_SQL);
            preparedStatement.setInt(PERMITTED_PERMITTED_NUMBER_SQL_NUMBER, permittedNumber);
            preparedStatement.setString(EMAIL_PERMITTED_NUMBER_SQL_NUMBER, email);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * getAuthors
     * get identifiers of authors (email)
     * @return list of author's identifiers (email)
     */
    public List<String> getAuthors (){
        List<String> authorsList = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_REGISTERED_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(USER_IS_AUTHOR_COLUMN_NAME) == IS_AUTHOR){
                    authorsList.add(resultSet.getString(USER_EMAIL_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return authorsList;
    }
    
    /**
     * getReaders
     * returns identifiers of simple users
     * @return list of reader's identifiers
     */
    public List<String> getReaders (){
        List<String> authorsList = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_REGISTERED_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(USER_IS_AUTHOR_COLUMN_NAME) == IS_AUTHOR_DEFAULT){
                    authorsList.add(resultSet.getString(USER_EMAIL_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return authorsList;
    }
    
    /**
     * getSimpleUsers 
     * returns users which are haven't administrator's rights
     * @return list of simple users
     */
    public List<String> getSimpleUsers (){
        List<String> authorsList = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_REGISTERED_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(USER_IS_ADMIN_COLUMN_NAME) == IS_ADMIN_DEFAULT){
                    authorsList.add(resultSet.getString(USER_EMAIL_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return authorsList;
    }
    
    /**
     * getAdminUsers
     * returns users, which are have administrator's rights
     * @return list of users-administrators
     */
    public List<String> getAdminUsers (){
        List<String> authorsList = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_REGISTERED_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(USER_IS_ADMIN_COLUMN_NAME) == IS_ADMIN){
                    authorsList.add(resultSet.getString(USER_EMAIL_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return authorsList;
    }
    
    /**
     * getAllUsers
     * returns list of all registered users
     * @return list of users
     */
    public List<String> getAllUsers (){
        List<String> usersList = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(GET_ALL_REGISTERED_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(resultSet.getString(USER_EMAIL_COLUMN_NAME));
            }
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return usersList;
    }
    
    /**
     * deleteUser
     * deletes user from the data base, using user's email
     * @param email - user's identifier
     */
    public void deleteUser (String email){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
    
    /**
     * setBlockUser
     * block user
     * @param email - user's identifier
     */
    public void setBlockUser (String email, int block){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(SET_BLOCK_USER_SQL);
            preparedStatement.setInt(BLOCK_SET_BLOCK_USER_SQL_NUMBER, block);
            preparedStatement.setString(EMAIL_SET_BLOCK_USER_SQL_NUMBER, email);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
       
    /**
     * setAuthorRights
     * sets author's rights
     * @param email - user's identifier
     */
    public void setAuthorRights (String email){
        try (Connection connection = jdbcUtils.getConnection();){
            preparedStatement = connection.prepareStatement(SET_AUTHOR_RIGHTS_SQL);
            preparedStatement.setInt(IS_AUTHOR_SET_AUTHOR_RIGHTS_SQL_NUMBER, IS_AUTHOR);
            preparedStatement.setString(EMAIL_SET_AUTHOR_RIGHTS_SQL_NUMBER, email);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            configLog.init();
            logger.info(LOG_SQL_EXCEPTION_MESSAGE + UserDao.class.getName());
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
