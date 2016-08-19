/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.LOCALE_SESSION_KEY;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static controller.constants.ConstantsController.*;

/**
 *UncheckedArticlePageCommand
 * @author Zakhar
 */
public class UncheckedArticlePageCommand implements Command {

    /**
     * returns unchecked article page
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
        if (!homePageCommand.isChangingLanguage()){
            sessionUser.setAttribute(ARTICLES_NOT_PERMITTED_NAME, Command.decodeParameter(request.getParameter(ARTICLES_NOT_PERMITTED)));
        }
        request.setAttribute(ARTICLE_AUTHOR_NAME_FIELD, userService.getAuthorFullName((String) sessionUser.getAttribute(ARTICLES_NOT_PERMITTED_NAME)));
        request.setAttribute(ARTICLE_DATE_FIELD, articlesService.getArticleDate((String) sessionUser.getAttribute(ARTICLES_NOT_PERMITTED_NAME)));
        request.setAttribute(ARTICLE_NAME_FIELD, sessionUser.getAttribute(ARTICLES_NOT_PERMITTED_NAME));
        request.setAttribute(ARTICLE_REVIEW_FIELD, articlesService.getAboutArticleText((String) sessionUser.getAttribute(ARTICLES_NOT_PERMITTED_NAME)));
        request.setAttribute(ARTICLE_TEXT_FIELD, articlesService.getArticleText((String) sessionUser.getAttribute(ARTICLES_NOT_PERMITTED_NAME)));
        request.setAttribute(PERMIT_UNCHECKED, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERMIT_SUBMIT));
        request.setAttribute(DELETE_UNCHECKED, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        request.setAttribute(CLOSE_UNCHECKED, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_CLOSE_SUBMIT));
        homePageCommand.setChangingLanguage(false);
        return userPageCommand.execute(request, response);
    }
    
}
