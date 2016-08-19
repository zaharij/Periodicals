/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import static model.connection.constants.ConnectConstantsDB.*;

/**
 *
 * @author Zakhar
 */
public class JDBCUtils {
    private static final Logger log = Logger.getLogger(JDBCUtils.class.getName());
    private DataSource dataSource;

    public void init(String dataSourceName) {
        try {
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(dataSourceName);
        } catch (NamingException e) {
            log.log(Level.SEVERE, JNDI_EXCEPTION, e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException(DATA_SOURCE_IS_NULL_MESSAGE);
        }
        return dataSource.getConnection();
    }
}
