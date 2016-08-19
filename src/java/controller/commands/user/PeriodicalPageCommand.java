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
 *PeriodicalPageCommand
 * @author Zakhar
 */
public class PeriodicalPageCommand implements Command {

    /**
     * returns periodicals page
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
        if (request.getParameter(SUBJECT_OPEN) != null){
            sessionUser.setAttribute(SUBJECT_OPEN, Command.decodeParameter(request.getParameter(SUBJECT_OPEN)));
        }
        request.setAttribute(TITLE_PERIODICAL, sessionUser.getAttribute(SUBJECT_OPEN));
        request.setAttribute(SUBSCRIPTION_DATE, periodicalsService.getDateEndSubscription((String) sessionUser.getAttribute(EMAIL)
                , (String) sessionUser.getAttribute(SUBJECT_OPEN)));
        request.setAttribute(ARTICLES, articlesService.getArticleNamesByPeriodical((String) sessionUser.getAttribute(SUBJECT_OPEN)));
        request.setAttribute(REPLENISH_ACCOUNT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_ACCOUNT_SUBMIT));
        request.setAttribute(LOGOUT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute(BACK_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BACK_SUBMIT));
        request.setAttribute(DATE_SUBSCRIPTION_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SUBSCRIPTION_DATE_INFO));
        request.setAttribute(AVAILABLE_ARTICLES_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AVAILABLE_ARTICLES_INFO));
        request.setAttribute(OPEN_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_OPEN_SUBMIT));
        return PERIODICAL_JSP;
    }
}
