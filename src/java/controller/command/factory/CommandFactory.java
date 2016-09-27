/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command.factory;

import controller.commands.Command;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CommandFactory
 * commands for servlet
 * implements pattern Command
 * @author Zakhar
 */
public class CommandFactory {

    /**
     * getCommand
     * get command from request
     * @param request
     * @param response
     * @return current command object
     * @throws ServletException
     * @throws IOException 
     */
    public Command getCommand (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            if (COMMAND_MAP.containsKey(entry.getKey())){
                return COMMAND_MAP.get(entry.getKey());
            } else if (entry.getKey().startsWith(LANGUAGE_STR)){
                return COMMAND_MAP.get(LANGUAGE_STR);
            }        
        }
        return null;
    }

    /**
     * @return the login
     */
    public Command getLogin() {
        return COMMAND_MAP.get(LOGIN_USER);
    }

    /**
     * @return the homePage
     */
    public Command getHomePage() {
        return COMMAND_MAP.get(START_PAGE);
    }

    /**
     * @return the language
     */
    public Command getLanguage() {
        return COMMAND_MAP.get(LANGUAGE);
    }

    /**
     * @return the loginAdmin
     */
    public Command getLoginAdmin() {
        return COMMAND_MAP.get(LOGIN_ADMIN);
    }

    /**
     * @return the loginPage
     */
    public Command getLoginPage() {
        return COMMAND_MAP.get(SUPERADMIN);
    }

    /**
     * @return the registerForm
     */
    public Command getRegisterForm() {
        return COMMAND_MAP.get(REGISTER_USER);
    }

    /**
     * @return the register
     */
    public Command getRegister() {
        return COMMAND_MAP.get(REGISTER_DO);
    }
    
}
