/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import controller.commands.superadmin.*;
import controller.commands.user.*;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.*;

/**
 *
 * @author Zakhar
 */
public interface Command {
    UserService userService = new UserService();
    AdminService adminService = new AdminService();
    ArticlesService articlesService = new ArticlesService();
    PeriodicalsService periodicalsService = new PeriodicalsService();
    AdminPageCommand adminPageCommand = new AdminPageCommand();
    UserManagementCommand userManagementCommand = new UserManagementCommand();
    LoginPageCommand loginPageCommand = new LoginPageCommand();
    NameStatusCommand nameStatusCommand = new NameStatusCommand();
    PeriodicalPageCommand periodicalPageCommand = new PeriodicalPageCommand();
    UserPageCommand userPageCommand = new UserPageCommand();
    HomePageCommand homePageCommand = new HomePageCommand();
    RegisterFormCommand registerFormCommand = new RegisterFormCommand();   
    PeriodicalUnsignedCommand periodicalUnsignedCommand = new PeriodicalUnsignedCommand();
    ArticlePageCommand articlePageCommand = new ArticlePageCommand();
    ReplenishAccountPageCommand replenishAccountPageCommand = new ReplenishAccountPageCommand();
    WriteArticlePageCommand writeArticlePageCommand = new WriteArticlePageCommand();
    ViewArticleCommand viewArticleCommand = new ViewArticleCommand();
    UncheckedArticlePageCommand uncheckedArticlePageCommand = new UncheckedArticlePageCommand();
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
    
    /**
     * decodeParameter
     * decoding request parameters
     * @param parameter
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String decodeParameter(String parameter) throws UnsupportedEncodingException {
        if (parameter != null){
            return new String(parameter.getBytes(ISO_ENCOD),UTF8_ENCOD);
        }
        return EMPTY_STR;
    }
}
