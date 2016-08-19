/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.SUBJECT;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *DeletePeriodicalCommand
 * @author Zakhar
 */
public class DeletePeriodicalCommand implements Command {

    /**
     * execute
     * deletes periodical
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        periodicalsService.deletePeriodical(Command.decodeParameter(request.getParameter(SUBJECT)));
        return adminPageCommand.execute(request, response);
    }
}
