/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.ARTICLES_NOT_PERMITED;
import static controller.constants.ConstantsController.ARTICLES_NOT_PERMITED_NAME;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *DeleteUnpermitedArticleCommand
 * @author Zakhar
 */
public class DeleteUnpermitedArticleCommand implements Command{

    /**
     * execute
     * deletes unpermited article
     * @param request
     * @param response
     * @return admin page (str)
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        articlesService.deleteArticle(Command.decodeParameter(request.getParameter(ARTICLES_NOT_PERMITED)));
        session.setAttribute(ARTICLES_NOT_PERMITED_NAME, null);
        return adminPageCommand.execute(request, response);
    }
}
