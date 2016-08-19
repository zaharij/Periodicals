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
 *UserPageCommand
 * @author Zakhar
 */
public class UserPageCommand implements Command{

    /**
     * RETURNS USER PAGE
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
        request.setAttribute(PERIODICALS, periodicalsService.getAllPeriodicalsNames());
        request.setAttribute(PERIODICALS_PAYED, periodicalsService.getAllPeriodicalsNames((String) sessionUser.getAttribute(EMAIL)));
        request.setAttribute(AUTH_NUM, userService.getAuthorsNumber());       
        if (request.getParameter(BACK_TO_USER) != null){
            sessionUser.setAttribute(ARTICLES_NOT_PERMITTED_NAME, null);
        }
        if (homePageCommand.isChangingLanguage() && sessionUser.getAttribute(ARTICLES_NOT_PERMITTED_NAME) != null){
            uncheckedArticlePageCommand.execute(request, response);
        }
        if ((boolean) sessionUser.getAttribute(IS_ADMIN)){
            request.setAttribute(ARTICLES_NOT_PERMITTED, articlesService.getNotPermittedArticles());;
        }
        request.setAttribute(REPLENISH_ACCOUNT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_ACCOUNT_SUBMIT));
        request.setAttribute(LOGOUT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute(PERIODICALS_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERIODICALS));
        request.setAttribute(AVAILABLE_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AVAILABLE));
        request.setAttribute(SIGNED_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SIGNED));
        request.setAttribute(WRITE_ARTICLES_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_WRITE_ARTICLE));
        request.setAttribute(ADDON_INFO_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADDON_INFO_SUBMIT));
        request.setAttribute(OPEN_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_OPEN_SUBMIT));
        request.setAttribute(CHOOSE_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_CHOOSE_SUBMIT));
        homePageCommand.setChangingLanguage(false);
        return USER_JSP;
    }
}
