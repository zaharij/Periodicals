/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl.constants;

/**
 *
 * @author Zakhar
 */
public class ConstantsDaoImpl {
    public final static int DEFAULT_VALUE = 0;
    public final static double DEFAULT_ACCOUNT_MONEY = 0;
    public final static int FREEZE_MONEY_DEFAULT_PERIOD_IN_MONTHES = 1;// default period for salaries (monthes)
    public final static int RESULT_WELL_DONE = 1;
    public final static int RESULT_SQL_EXCEPTION = -1;
    public final static int RESULT_CONDITIONS_IS_NOT_TRUE = 0;
    public final static int IS_PASSWORD_DEFAULT = 0;
    public final static int IS_ADMIN = 1;
    public final static int IS_ADMIN_DEFAULT = 0;
    public final static int USER_PERMITTED_ARTICLES_DEFAULT = 0;
    public final static int USER_PERMITTED_ARTICLES_MAX = 5;
    public final static int IS_AUTHOR_DEFAULT = 0;
    public final static int IS_AUTHOR = 1;
    public final static int IS_BLOCKED_DEFAULT = 0;
    public final static int IS_BLOCKED = 1;
        
    public final static int ARTICLE_IS_PERMITTED = 1;
    public final static int ARTICLE_IS_NOT_PERMITTED = 0;
    
    public final static String LOG_IMG_ID_COLUMN_NUMBER = "id";
    public final static String LOG_IMG_VALUE_COLUMN_NUMBER = "value";
    
    public final static String USER_FIRST_NAME_COLUMN_NAME = "first_name";
    public final static String USER_LAST_NAME_COLUMN_NAME = "last_name";
    public final static String USER_MIDDLE_NAME_COLUMN_NAME = "middle_name";
    public final static String USER_EMAIL_COLUMN_NAME = "email";
    public final static String USER_PASSWORD_COLUMN_NAME = "password";
    public final static String USER_ACCOUNT_COLUMN_NAME = "account";
    public final static String USER_IS_ADMIN_COLUMN_NAME = "is_admin";
    public final static String USER_IS_AUTHOR_COLUMN_NAME = "is_author";
    public final static String USER_BLOCK_COLUMN_NAME = "block";
    public final static String USER_PERMITTED_COLUMN_NAME = "permitted";
    public final static String LOG_SQL_EXCEPTION_MESSAGE = "SQL Exception: ";
    
    public final static String PERIODICALS_SUBJECT_COLUMN_NUMBER = "subject";
    public final static String PERIODICALS_PRICE_COLUMN_NUMBER = "price";
    public final static String PERIODICALS_FOLLOWERSNUM_COLUMN_NUMBER = "followersnum";
    public final static String PERIODICALS_ARTICLENUM_COLUMN_NUMBER = "articlenum";
    public final static String PERIODICALS_MONEY_COLUMN_NUMBER = "money";
    public final static String PERIODICALS_AUTHORSNUM_COLUMN_NUMBER = "authorsnum";
           
    public final static String ADMIN_LOGIN_COLUMN_NUMBER = "login";
    public final static String ADMIN_PASSWORD_COLUMN_NUMBER = "password";
    
    public final static String FOLLOWERS_EMAIL_COLUMN_NAME = "email";
    public final static String FOLLOWERS_PERIODICAL_COLUMN_NAME = "periodical";
    public final static String FOLLOWERS_MONEY_COLUMN_NAME = "money";
    public final static String FOLLOWERS_DATE_COLUMN_NAME = "date";
    public final static String FOLLOWERS_MOUNTHPRICE_COLUMN_NAME = "monthprice";
    public final static String FOLLOWERS_DATE_FREEZ_COLUMN_NAME = "nextdate";
        
    public final static String ARTICLES_PERIODICAL_COLUMN_NAME = "periodical";
    public final static String ARTICLES_TITLE_COLUMN_NAME = "title";
    public final static String ARTICLES_AUTHOR_COLUMN_NAME = "author";
    public final static String ARTICLES_DATE_COLUMN_NAME = "date";
    public final static String ARTICLES_ABOUT_ARTICLE_COLUMN_NAME = "about";
    public final static String ARTICLES_TEXT_COLUMN_NAME = "text";
    public final static String ARTICLES_PERMISSION_COLUMN_NAME = "permission";
}
