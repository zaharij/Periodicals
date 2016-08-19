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
 *NameStatusCommand
 * @author Zakhar
 */
public class NameStatusCommand implements Command{

    /**
     * RETURNS user's name, admin rights, author rights, and money account
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
        request.setAttribute(NAME_FIELD, sessionUser.getAttribute(USERNAME));
        request.setAttribute(ACCOUNT, userService.getUserMoney((String) sessionUser.getAttribute(EMAIL)));
        if ((boolean) sessionUser.getAttribute(IS_WRITER)){
            request.setAttribute(IS_AUTHOR_FIELD, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AUTHOR));
        }
        if ((boolean) sessionUser.getAttribute(IS_ADMIN)){
            request.setAttribute(IS_ADMIN_FIELD, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADMIN));
        }
        return null;
    }
    
}
