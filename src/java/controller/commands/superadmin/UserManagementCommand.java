/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static controller.constants.ConstantsController.*;
import java.util.Locale;

/**
 *UserManagementCommand
 * @author Zakhar
 */
public class UserManagementCommand implements Command {

    /**
     * execute
     * gets user management page
     * @param request
     * @param response
     * @return user management page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        request.setAttribute(LOGIN_FIELD, sessionAdmin.getAttribute(LOGIN));
        request.setAttribute(ADMINS, userService.getAdminUsers());
        request.setAttribute(USERS_SIMPLE, userService.getSimpleUsers());
        request.setAttribute(USER_ALL, userService.getAllUsers());
        request.setAttribute(USERS_UNBLOCKED, userService.getUnblockedUsers());
        request.setAttribute(USERS_BLOCKED, userService.getBlockedUsers());
        request.setAttribute(LOGOUT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute(PERIODICALS_MANAGEMENT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERIODICALS_MANAGEMENT));
        request.setAttribute(SET_ADMIN_RIGHTS_MESSAGE, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERIODICALS_SET_ADMIN_RIGHTS));
        request.setAttribute(SET_ADMIN_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SET_ADMIN_SUBMIT));
        request.setAttribute(UNSET_ADMIN_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_UNSET_ADMIN_SUBMIT));
        request.setAttribute(DELETE_USER_MESSAGE, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_USER));
        request.setAttribute(UNBLOCK_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_UNBLOCK_SUBMIT));
        request.setAttribute(BLOCK_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BLOCK_SUBMIT));
        request.setAttribute(DELETE_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        request.setAttribute(PAY_MESSAGE, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PAY_MESSAGE));
        request.setAttribute(PAY_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PAY_SUBMIT));
        request.setAttribute(BLOCK_MESSAGE, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BLOCK_USER));
        return SUPERADMINUSER_JSP;
    }
}
