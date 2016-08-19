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
 *DeleteUnpermitedArticleUserCommand
 * @author Zakhar
 */
public class DeleteUnpermitedArticleUserCommand implements Command{

    /**
     * deletes unpermitted article
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        articlesService.deleteArticle(Command.decodeParameter(request.getParameter(ARTICLES_NOT_PERMITTED)));
        session.setAttribute(ARTICLES_NOT_PERMITTED_NAME, null);
        return userPageCommand.execute(request, response);
    }
}
