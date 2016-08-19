/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.connection.constants;

/**
 *
 * @author Zakhar
 */
public class ConnectConstantsDB {
    public final static String JNDI_NAME = "jdbc/sample";
    public final static String JNDI_EXCEPTION= "JNDIException: {0}";
    public static final String DATA_SOURCE_IS_NULL_MESSAGE = "DataSource is null.";
            
    public final static String REGISTER_DRIVER = "com.mysql.jdbc.Driver";
    
    public final static int CONNECTION_POOL_MIN_SIZE = 8;
    public final static int CONNECTION_POOL_MAX_SIZE = 16;
    public final static int DEFAULT_NUMBER = 0;
}
