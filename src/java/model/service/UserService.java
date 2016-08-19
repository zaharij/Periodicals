/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.dao.impl.ArticlesDao;
import model.dao.impl.UserDao;
import model.entities.User;
import static model.service.constants.ConstantsLogic.*;

/**
 *
 * @author Zakhar
 */
public class UserService {
    private UserDao userDao = new UserDao();
    private ArticlesDao articlesDao = new ArticlesDao();
    private Pattern pattern;
    private Matcher matcher;
    
    /**
     * getRandomLogImgName
     * returns random name of login picture
     * @return random name
     */
    public String getRandomLogImgName(){
        Integer random = new Random().nextInt(LOG_IMG_NUMBER);
        return random.toString();
    }
    /**
     * createUser
     * creates new user
     * @param user User object, which contains all needed parameters
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean createUser(User user){
        try{
            userDao.insertUserIntoDb(user);
            return true;
        }catch (RuntimeException ex){
            return false;
        }        
    }
    
    /**
      * replenishAccount
      * replenish user's account
      * @return result (boolean)
      */
     public boolean replenishAccount(String email, double money) {
         try{
             double newCash = userDao.getUserMoney(email) + money;
             userDao.setMoneyToAccount(email, newCash);
             return true;
         }catch (RuntimeException ex) {
             return false;
         }                
     }
     
     /**
     * setArticlePermission
     * 
      * @param title
      * @return 
      */
    public boolean setArticlePermission (String title){
        try{
            String email = articlesDao.getArticleEmailAuthor(title);
            int permittedNumber = userDao.getUserPermittedNumber(email) + ARTICLE_IS_PERMITTED;
            if (permittedNumber >= USER_PERMITTED_ARTICLES_MAX){
                userDao.setAuthorRights(email);
            }
            userDao.updatePermittedNumber(email, permittedNumber);
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }
    
    /**
     * lofinIsFree
     * check if current user is already registered
     * @return true if user is already registered, and false if no
     */
    public boolean loginIsFree(String login){
        if (userDao.getUserPassword(login) != IS_PASSWORD_DEFAULT){
            return true;
        }
        return false;
    }
    
    /**
     * checkLogin
     * check if password for user is true
     * @return result (boolean)
     */
    public boolean checkLogin(String login, int passwordInput){
        try {
            int passwordTrue = userDao.getUserPassword(login);
            if (passwordTrue == passwordInput){
                return true;
            } else{
                return false;
            }
        } catch (RuntimeException ex){
            return false;
        }
    }
    
    /**
     * checkRegisterImg
     * check register if it is a human
     * @return result (boolean)
     */
     public boolean checkRegisterImg(String idImgName, String inputValue) {
         try {
            Integer idImgNameInt = Integer.parseInt(idImgName);
            String trueValue = userDao.getLoginImgValue(idImgNameInt);
            if (inputValue.equals(trueValue)){
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException ex){
            return false;
        }
    }
     
     /**
      * checkRegFields
      * registration forms validation
      * @return result (boolean)
      */
     public boolean checkRegFields(String firstName, String lastName, String fatherName, String email, String password){
        if (checkField(firstName, CHECK_NAME_REG) && checkField(lastName, CHECK_NAME_REG) 
                && fatherName.equals("") ? true : checkField(fatherName, CHECK_NAME_REG)
                && checkLoginFields(email, password)){
            return true;
        }
        return false;
     }
     
     /**
      * checkLoginFields
      * check if login fields is correct
      * @param email
      * @param password
      * @return result (boolean)
      */
     public boolean checkLoginFields(String email, String password){
        if (checkField(email, CHECK_EMAIL_REG) && checkField(password, CHECK_PASSWORD_REG)){
            return true;
        }
        return false;
     }
     
     /**
      * getWriterRights
      * check if user has rights as writer
      * @param email
      * @return result (boolean)
      */
     public boolean getWriterRights (String email){
         try{
            if (userDao.getWriterRights(email) != IS_WRITER_DEFAULT){
                return true;
            } else {
                return false;
            }
         } catch (RuntimeException ex){
             return false;
         }
     }
     
     /**
      * getAdminRights
      * check if user has rights as admin
      * @param email
      * @return result (boolean)
      */
     public boolean getAdminRights (String email){
        try {
            if (userDao.getAdminRights(email) != IS_ADMIN_DEFAULT){
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException ex){
            return false;
        }
     }
     
     /**
      * getFullUserName
      * get user's full name from db 
      * @param email
      * @return full name (String)
      */
     public String getFullUserName (String email){
         try {
            return userDao.getUserFirstName(email) + WHITESPACE + userDao.getUserLastName(email);
         } catch (RuntimeException ex){
             return null;
         }
     }
     
     /**
     * checkField
     * check if given field is correct
     * @param fieldInput inputed string
     * @param regularExpression regular expression to check
     * @return result (boolean)
     */
    public boolean checkField(String fieldInput, String regularExpression) {
        pattern = Pattern.compile(regularExpression);
        matcher = pattern.matcher(fieldInput);
        if (!matcher.matches()){
            return false;
        }
        return true;
    }
    
    /**
     * checkReplenishField
     * returns the boolean result if replenish field is correct
     * @param money - input string of money
     * @return result (boolean)
     */
    public boolean checkReplenishField(String money){
        return checkField(money, CHECK_MONEY_REG);
    }
    /**
     * getAuthorsNumber
     * get number of authors
     * @return result
     */
     public int getAuthorsNumber(){
         try{
            return userDao.getAuthorsNumber();
         }catch (RuntimeException ex){
             return IS_ADMIN_DEFAULT;
         }
     }
     
     /**
      * getAuthorFullName
      * get author's full name
      * @return  name
      */
     public String getAuthorFullName (String title){
         try {
            String email = articlesDao.getArticleEmailAuthor(title);
            return userDao.getUserFirstName(email) + " " + userDao.getUserLastName(email);
         } catch (RuntimeException ex){
             return null;
         }
     }
     
     /**
     * getReaders
     * get all readers
     * @return list of readers
     */
    public List<String> getReaders(){
        try {
            return userDao.getReaders();
        } catch (RuntimeException ex){
            return null;
        }
    }
    
    /**
     * getAuthors
     * get all authors
     * @return list of authors
     */
    public List<String> getAuthors (){
        try {
            return userDao.getAuthors();
        } catch (RuntimeException ex){
            return null;
        }
    }
    
    /**
     * getSimpleUsers
     * get all simple users
     * @return list of readers
     */
    public List<String> getSimpleUsers(){
        return userDao.getSimpleUsers();
    }
    
    /**
     * getAuthors
     * get all admins among users
     * @return list of authors
     */
    public List<String> getAdminUsers (){
        try {
            return userDao.getAdminUsers();
        }catch (RuntimeException ex){
            return null;
        }
    }
    
    /**
     * getAllUsers
     * get all admins among users
     * @return list of authors
     */
    public List<String> getAllUsers (){
        try {
            return userDao.getAllUsers();
        }catch (RuntimeException ex){
            return null;
        }
    }
    
    /**
     * deleteUser 
     * delete user from db
     * @param email 
     */
    public void deleteUser (String email){
        try {
            userDao.deleteUser(email);
        } catch (RuntimeException ex){
        }
    }
    
    /**
     * setBlockTrueUser
     */
    public void setBlockTrueUser (String email){
        try {
            userDao.setBlockUser(email, IS_BLOCKED);
        } catch (RuntimeException ex){
        }
    }
    
    /**
     * setBlockFalseUser
     */
    public void setBlockFalseUser (String email){
        try {
            userDao.setBlockUser(email, IS_BLOCKED_DEFAULT);
        } catch (RuntimeException ex){
        }
    }
    
    /**
     * getBlockedUsers
     * get all blocked users
     * @return list of authors
     */
    public List<String> getBlockedUsers (){
        try {
            return userDao.getBlockedUsers();
        } catch (RuntimeException ex){
            return null;
        }
    }
    
     /**
     * getUnblockedUsers
     * get all unblocked users
     * @return list of authors
     */
    public List<String> getUnblockedUsers (){
        try {
            return userDao.getUnblockedUsers();
        } catch (RuntimeException ex){
            return null;
        }
    }
    
     /**
     * getUserMoney
     * <p>
     * returns all money user has
     * </p>
     * @param email - user's email, to identify current user
     * return all money (double), or (-1) if it is SQL Exception
     */
    public String getUserMoney(String email){
        try {
            Double money = userDao.getUserMoney(email);
            return money.toString();
        } catch (RuntimeException ex){
            return null;
        }
    }
}
