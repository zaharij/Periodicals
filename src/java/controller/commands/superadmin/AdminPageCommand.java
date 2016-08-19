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
import java.util.ResourceBundle;

/**
 *AdminPageCommand
 * gets admin page
 * @author Zakhar
 */
public class AdminPageCommand implements Command {

    /**
     * execute
     * gets admin page
     * @param request
     * @param response
     * @return PAGE (STRING)
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        request.setAttribute(LOGIN_FIELD, sessionAdmin.getAttribute(LOGIN));
        request.setAttribute(PERIODICALS, periodicalsService.getAllPeriodicalsNames());
        request.setAttribute(ARTICLES_NOT_PERMITTED, articlesService.getNotPermittedArticles());
        request.setAttribute(OBJECT_LIST, articlesService.getNotPermittedArticles());
        request.setAttribute(CREATE_NEW_PUBLICATION_MESSAGE, getResourceBundle(locale, LOCALE_KEY_CREATE_PERIODICAL));
        request.setAttribute(DELETE_PERIODICAL_ARTICLE_MESSAGE, getResourceBundle(locale, LOCALE_KEY_DELETE_PERIODICAL_ARTICLE));
        request.setAttribute(PERMIT_ARTICLE_MESSAGE, getResourceBundle(locale, LOCALE_KEY_PERMIT_ARTICLE));
        request.setAttribute(TITLE_MESSAGE, getResourceBundle(locale, LOCALE_KEY_SUBJECT));
        request.setAttribute(PRICE_MESSAGE, getResourceBundle(locale, LOCALE_KEY_PRICE));
        request.setAttribute(LOGOUT_SUBMIT, getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute(USER_MANAGEMENT_SUBMIT, getResourceBundle(locale, LOCALE_KEY_USER_MANAGEMENT_BUTTON));
        request.setAttribute(CREATE_SUBMIT, getResourceBundle(locale, LOCALE_KEY_CREATE_SUBMIT));
        request.setAttribute(DELETE_SUBMIT, getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        request.setAttribute(DELETE_ARTICLE_SUBMIT, getResourceBundle(locale, LOCALE_KEY_DELETE_ARTICLE_SUBMIT));
        request.setAttribute(VIEW_SUBMIT, getResourceBundle(locale, LOCALE_KEY_VIEW_SUBMIT));
        request.setAttribute(PERMIT_SUBMIT, getResourceBundle(locale, LOCALE_KEY_PERMIT_SUBMIT));
        return SUPERADMIN_JSP;
    }
 
    /**
     * getResourceBundle
     * returns localized String
     * @param locale - Locale object
     * @param localeKey - string's key
     * @return localized String
     */
    public String getResourceBundle(Locale locale, String localeKey){
        return ResourceBundle.getBundle(BUNDLE_DIR, locale).getString(localeKey);
    }
}
