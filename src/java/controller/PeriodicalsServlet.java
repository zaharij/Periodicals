/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.command.factory.CommandFactory;
import controller.commands.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static controller.constants.ConstantsController.ERROR_PAGE;

/**
 *PeriodicalsServlet
 * @author Zakhar
 */
public class PeriodicalsServlet extends HttpServlet {
    private CommandFactory userCommandFactory = new CommandFactory();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = userCommandFactory.getCommand(request, response);
        if (command != null){
            forward(command.execute(request, response), request, response);
        } else {
            forward(ERROR_PAGE, request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    public void forward (String redirectTo, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(redirectTo).forward(request, response);
    }
}
