/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *ReplenishCommand
 * @author Zakhar
 */
public class ReplenishCommand implements Command {

    /**
     * REPLENISHES ACCOUNT
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
        if (userService.checkReplenishField((String) request.getParameter(MONEY))){
            userService.replenishAccount((String) sessionUser.getAttribute(EMAIL), Double.parseDouble((String) request.getParameter(MONEY)));
        } else {
            request.setAttribute(FIELD_FAIL_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_VALUES_NOT_CORRECT));
            return replenishAccountPageCommand.execute(request, response);
        }
        return userPageCommand.execute(request, response);
    }
}
