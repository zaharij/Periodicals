/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.PRICE_PERIODICAL;
import static controller.constants.ConstantsController.TITLE_PERIODICAL;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entities.Periodical;

/**
 *SetPeriodicalCommand
 * @author Zakhar
 */
public class SetPeriodicalCommand implements Command {
    private Periodical periodical;

    /**
     * execute
     * creates new periodical
     * @param request
     * @param response
     * @return admin's page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        periodical = new Periodical();
        periodical.setTheme(Command.decodeParameter(request.getParameter(TITLE_PERIODICAL)));
        periodical.setMounthPrice(Double.parseDouble(request.getParameter(PRICE_PERIODICAL)));
        periodicalsService.setPeriodical(periodical);
        return adminPageCommand.execute(request, response);
    }
}
