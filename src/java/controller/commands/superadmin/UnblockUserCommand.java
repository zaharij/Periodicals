/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.BLOCKED_USERS;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *UnblockUserCommand
 * @author Zakhar
 */
public class UnblockUserCommand implements Command {

    /**
     * execute
     * unblocks user
     * @param request
     * @param response
     * @return admin's page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.setBlockFalseUser(request.getParameter(BLOCKED_USERS));
        return userManagementCommand.execute(request, response);
    }
}
