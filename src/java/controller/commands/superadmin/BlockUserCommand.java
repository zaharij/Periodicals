/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.UNBLOCKED_USER;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *BlockUserCommand
 * @author Zakhar
 */
public class BlockUserCommand implements Command{

    /**
     * execute
     * overriding method, implements command pattern
     * @param request
     * @param response
     * @return page (string)
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.setBlockTrueUser(request.getParameter(UNBLOCKED_USER));
        return userManagementCommand.execute(request, response);
    }
}
