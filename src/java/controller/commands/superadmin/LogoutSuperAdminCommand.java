/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *LogoutSuperAdminCommand
 * @author Zakhar
 */
public class LogoutSuperAdminCommand implements Command {

    /**
     * execute
     * logout superadmin
     * @param request
     * @param response
     * @return main page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        sessionAdmin.invalidate();
        return loginPageCommand.execute(request, response);
    }
}
