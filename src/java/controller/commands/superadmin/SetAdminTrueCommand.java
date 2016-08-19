/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.USERS_SIMPLE;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *SetAdminTrueCommand
 * @author Zakhar
 */
public class SetAdminTrueCommand implements Command {
    
    /**
     * execute
     * sets admin rights
     * @param request
     * @param response
     * @return admin's page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        adminService.setAdminRightsTrue(request.getParameter(USERS_SIMPLE));
        return userManagementCommand.execute(request, response);
    }
}
