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

/**
 *PayForArticlesCommand
 * @author Zakhar
 */
public class PayForArticlesCommand implements Command {

    /**
     * execute
     * pays for articles
     * @param request
     * @param response
     * @return admin's page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        adminService.payForArticles();
        return userManagementCommand.execute(request, response);
    }
}
