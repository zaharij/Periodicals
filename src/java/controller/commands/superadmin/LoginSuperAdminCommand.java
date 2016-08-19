/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.PASSWORD_SALT;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static controller.constants.ConstantsController.*;
import java.util.Locale;

/**
 *LoginSuperAdminCommand
 * @author Zakhar
 */
public class LoginSuperAdminCommand implements Command{

    /**
     * execute
     * logins supersdmin
     * @param request
     * @param response
     * @return superadmin page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        if (adminService.checkLoginFields(request.getParameter(LOGIN), request.getParameter(PASSWORD))){
            if (adminService.checkLogin(request.getParameter(LOGIN), (request.getParameter(PASSWORD) + PASSWORD_SALT).hashCode())){
                sessionAdmin.setAttribute(IS_LOGINED, true);
                sessionAdmin.setAttribute(LOGIN, request.getParameter(LOGIN));               
                return adminPageCommand.execute(request, response);
            } else{
                request.setAttribute(FIELD_FAIL_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGIN_NOT_CORRECT));
                return loginPageCommand.execute(request, response);
            }
        } else {
            request.setAttribute(FIELD_FAIL_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_VALUES_NOT_CORRECT));
            return loginPageCommand.execute(request, response);
        }
    }
}
