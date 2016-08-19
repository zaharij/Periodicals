/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

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
 *LoginCommand
 * @author Zakhar
 */
public class LoginCommand implements Command {
    
    /**
     * login user
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        if (userService.checkLoginFields(request.getParameter(EMAIL), request.getParameter(PASSWORD))){
            if (userService.checkLogin(request.getParameter(EMAIL), (request.getParameter(PASSWORD) + PASSWORD_SALT).hashCode())){
                sessionUser.setAttribute(IS_LOGINED, true);
                sessionUser.setAttribute(IS_WRITER, userService.getWriterRights(request.getParameter(EMAIL)));
                sessionUser.setAttribute(IS_ADMIN, userService.getAdminRights(request.getParameter(EMAIL)));
                sessionUser.setAttribute(USERNAME, userService.getFullUserName(request.getParameter(EMAIL)));
                sessionUser.setAttribute(EMAIL, request.getParameter(EMAIL));
                return userPageCommand.execute(request, response);
            } else{
                request.setAttribute(FIELD_FAIL_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGIN_NOT_CORRECT));
                return homePageCommand.execute(request, response);
            }
        } else {
            request.setAttribute(FIELD_FAIL_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_VALUES_NOT_CORRECT));
            return homePageCommand.execute(request, response);
        }
    }
    
}
