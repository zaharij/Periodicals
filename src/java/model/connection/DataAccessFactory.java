/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.connection;
import static model.connection.constants.ConnectConstantsDB.*;

/**
 *
 * @author Zakhar
 */
public class DataAccessFactory {
    private static final DataAccessFactory instance = new DataAccessFactory();
    private JDBCUtils jdbcUtil;

    private DataAccessFactory() {
    }

    public static DataAccessFactory getInstance() {
        return instance;
    }

    private JDBCUtils prepareJDBCUtils() {
        if (jdbcUtil == null) {
            jdbcUtil = new JDBCUtils();
            jdbcUtil.init(JNDI_NAME);
        }
        return jdbcUtil;
    }

    public static synchronized JDBCUtils getJDBCUtils() {
        return getInstance().prepareJDBCUtils();
    }
}
