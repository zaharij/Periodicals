/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.PERIODICAL_ARTICLES;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *DeleteArticleCommand
 * @author Zakhar
 */
public class DeleteArticleCommand implements Command {

    /**
     * execute
     * deletes article, implements command pattern
     * @param request
     * @param response
     * @return page (string)
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        articlesService.deleteArticle(Command.decodeParameter(request.getParameter(PERIODICAL_ARTICLES)));
        return adminPageCommand.execute(request, response);
    }
}
