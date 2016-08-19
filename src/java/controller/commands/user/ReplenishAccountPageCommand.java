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
 *ReplenishAccountPageCommand
 * @author Zakhar
 */
public class ReplenishAccountPageCommand implements Command {

    /**
     * RETURNS REPLENISH ACCOUNT PAGE
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
        nameStatusCommand.execute(request, response);
        request.setAttribute(LOGOUT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute(BACK_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BACK_SUBMIT));
        request.setAttribute(REPLENISH_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_ACCOUNT));
        request.setAttribute(REPLENISH_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_SUBMIT));
        return REPLENISH_JSP;
    }
}
