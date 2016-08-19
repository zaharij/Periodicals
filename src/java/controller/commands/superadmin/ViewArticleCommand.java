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
 *ViewArticleCommand
 * @author Zakhar
 */
public class ViewArticleCommand implements Command {

    /**
     * execute
     * opens article in admin's page
     * @param request
     * @param response
     * @return admin's page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        sessionAdmin.setAttribute(ARTICLES_NOT_PERMITED_NAME, Command.decodeParameter(request.getParameter(ARTICLES_NOT_PERMITED)));
        request.setAttribute(ARTICLE_AUTHOR_NAME_FIELD, userService.getAuthorFullName((String) sessionAdmin.getAttribute(ARTICLES_NOT_PERMITTED_NAME)));
        request.setAttribute(ARTICLE_DATE_FIELD, articlesService.getArticleDate((String) sessionAdmin.getAttribute(ARTICLES_NOT_PERMITED_NAME)));
        request.setAttribute(ARTICLE_NAME_FIELD, sessionAdmin.getAttribute(ARTICLES_NOT_PERMITTED_NAME));
        request.setAttribute(ARTICLE_REVIEW_FIELD, articlesService.getAboutArticleText((String) sessionAdmin.getAttribute(ARTICLE_NAME)));
        request.setAttribute(ARTICLE_TEXT_FIELD, articlesService.getArticleText((String) sessionAdmin.getAttribute(ARTICLES_NOT_PERMITED_NAME)));
        request.setAttribute(PERMIT_UNCHECKED, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERMIT_SUBMIT));
        request.setAttribute(DELETE_UNCHECKED, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        return adminPageCommand.execute(request, response);
    }
}
