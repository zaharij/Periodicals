/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static controller.constants.ConstantsController.*;
import java.util.Locale;

/**
 *LanguageCommand
 * @author Zakhar
 */
public class LanguageCommand implements Command {

    /**
     * SETS language
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        setLocaleToSesion(request, response);
        if (request.getParameter(LANGUAGE_USER) != null){
            homePageCommand.setChangingLanguage(true);
            page = userPageCommand.execute(request, response);   
        }else if (request.getParameter(LANGUAGE_REGISTER) != null){
            page = registerFormCommand.execute(request, response); 
        } else if (request.getParameter(LANGUAGE_PERIODICAL_UNSIGNED) != null){
            homePageCommand.setChangingLanguage(true);
            page = periodicalUnsignedCommand.execute(request, response);
        }else if (request.getParameter(LANGUAGE_PERIODICAL) != null){
            homePageCommand.setChangingLanguage(true);
            page = articlePageCommand.execute(request, response);
        } else if (request.getParameter(LANGUAGE_MAIN) != null){
            page = homePageCommand.execute(request, response);
        } else if (request.getParameter(LANGUAGE_REPLENISH) != null){
            page = replenishAccountPageCommand.execute(request, response);
        } else if (request.getParameter(LANGUAGE_WRITE_ARTICLE) != null){
            page = writeArticlePageCommand.execute(request, response);
        } else if (request.getParameter(LANGUAGE_ADMIN) != null){
            homePageCommand.setChangingLanguage(true);
            page = adminPageCommand.execute(request, response);
        } else if (request.getParameter(LANGUAGE_ADMIN_USER_MANAGEMENT) != null){
            page = userManagementCommand.execute(request, response);
        } else if (request.getParameter(LANGUAGE_ADMIN_LOGIN) != null) {
            page = loginPageCommand.execute(request, response);
        } 
        return page;
    }
    
    /**
     * setLocaleToSesion
     * set Locale object to the session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void setLocaleToSesion (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (request.getParameter(request.getParameterNames().nextElement()).toString().equalsIgnoreCase(LANGUAGE_INPUT_UK)){
            session.setAttribute(LOCALE_SESSION_KEY, new Locale(LANGUAGE_UK, COUNTRY_UA));
        } else {
            session.setAttribute(LOCALE_SESSION_KEY, new Locale(LANGUAGE_EN, COUNTRY_US));
        }
    }
}
