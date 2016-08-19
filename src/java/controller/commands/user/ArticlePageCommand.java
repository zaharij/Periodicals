/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *ArticlePageCommand
 * @author Zakhar
 */
public class ArticlePageCommand implements Command {

    /**
     * returns article page
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        if (request.getParameter(OPEN_ARTICLE_USER) != null || sessionUser.getAttribute(ARTICLE_NAME_USER) != null){
            if (request.getParameter(SUBJECT_OPEN_USER) != null){
                sessionUser.setAttribute(ARTICLES_USER, Command.decodeParameter(request.getParameter(SUBJECT_OPEN_USER)));
            }
            if (!homePageCommand.isChangingLanguage()){
                sessionUser.setAttribute(ARTICLE_NAME_USER, Command.decodeParameter(request.getParameter(ARTICLE_NAME_USER)));
            }
            request.setAttribute(ARTICLE_AUTHOR_NAME_FIELD, userService.getAuthorFullName((String) sessionUser.getAttribute(ARTICLE_NAME_USER)));
            request.setAttribute(ARTICLE_DATE_FIELD, articlesService.getArticleDate((String) sessionUser.getAttribute(ARTICLE_NAME_USER)));
            request.setAttribute(ARTICLE_NAME_FIELD, sessionUser.getAttribute(ARTICLE_NAME_USER));
            request.setAttribute(ARTICLE_REVIEW_FIELD, articlesService.getAboutArticleText((String) sessionUser.getAttribute(ARTICLE_NAME_USER)));
            request.setAttribute(ARTICLE_TEXT_FIELD, articlesService.getArticleText((String) sessionUser.getAttribute(ARTICLE_NAME_USER)));
        }
        homePageCommand.setChangingLanguage(false);
        return periodicalPageCommand.execute(request, response);
    }
}
