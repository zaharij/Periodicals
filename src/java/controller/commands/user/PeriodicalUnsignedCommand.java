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
 *PeriodicalUnsignedCommand
 * @author Zakhar
 */
public class PeriodicalUnsignedCommand implements Command {

    /**
     * RETURNS UNSIGNED PERIODICALS PAGE
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
        if (request.getParameter(SUBJECT) != null){
            sessionUser.setAttribute(SUBJECT, Command.decodeParameter(request.getParameter(SUBJECT)));
        }
        if (!homePageCommand.isChangingLanguage()){
            sessionUser.setAttribute(ARTICLE_NAME, Command.decodeParameter(request.getParameter(ARTICLE_NAME)));
        }
        request.setAttribute(TITLE_PERIODICAL, sessionUser.getAttribute(SUBJECT));
        request.setAttribute(CURRENT_ARTICLE_TITLE, sessionUser.getAttribute(ARTICLE_NAME));
        request.setAttribute(REVIEW, articlesService.getAboutArticleText((String) sessionUser.getAttribute(ARTICLE_NAME)));
        request.setAttribute(PRICE_PERIODICAL, periodicalsService.getPeriodicalPrice((String) sessionUser.getAttribute(SUBJECT)));
        request.setAttribute(ARTICLES_NUM, articlesService.getArticleNamesByPeriodical((String) sessionUser.getAttribute(SUBJECT)).size());
        request.setAttribute(ARTICLES, articlesService.getArticleNamesByPeriodical((String) sessionUser.getAttribute(SUBJECT)));
        request.setAttribute(MONTHES_COST, periodicalsService.getPeriodicalsMonthesCost((String) sessionUser.getAttribute(SUBJECT)));
        request.setAttribute(REPLENISH_ACCOUNT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_ACCOUNT_SUBMIT));
        request.setAttribute(LOGOUT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute(SIGNE_UP_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SIGNE_UP));
        request.setAttribute(ADDON_SIGNE_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADDON_SIGNE_UP_INFO));
        request.setAttribute(SIGNE_UP_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SIGNE_UP_SUBMIT));
        request.setAttribute(BACK_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BACK_SUBMIT));
        request.setAttribute(ADDON_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADDON_INFO));
        request.setAttribute(SUBJECT_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SUBJECT_INFO));
        request.setAttribute(PRICE_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PRICE_INFO));
        request.setAttribute(ARTICLES_NUMBER_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ARTICLES_NUMBER_INFO));
        request.setAttribute(FOLLOWERS_NUMBER_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_FOLLOWERS_NUMBER_INFO));
        request.setAttribute(AVAILABLE_ARTICLES_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AVAILABLE_ARTICLES_INFO));
        request.setAttribute(GET_ACQUINTED_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_GET_ACQUINTED_INFO));      
        homePageCommand.setChangingLanguage(false);
        return PERIODICALUNSIGNED_JSP;
    }
}
