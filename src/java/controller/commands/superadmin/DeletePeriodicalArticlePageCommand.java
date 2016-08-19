/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.ARTICLES;
import static controller.constants.ConstantsController.SUBJECT;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *DeletePeriodicalArticlePageCommand
 * @author Zakhar
 */
public class DeletePeriodicalArticlePageCommand implements Command{

    /**
     * execute
     * returns "delete articles" page
     * @param request
     * @param response
     * @return (String)
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ARTICLES, articlesService.getArticleNamesByPeriodical(Command.decodeParameter(request.getParameter(SUBJECT))));
        return adminPageCommand.execute(request, response);
    }
}
