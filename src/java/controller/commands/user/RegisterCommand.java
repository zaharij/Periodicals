/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.PASSWORD_SALT;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entities.User;
import static controller.constants.ConstantsController.*;
import java.util.Locale;
import javax.servlet.http.HttpSession;

/**
 *RegisterCommand
 * @author Zakhar
 */
public class RegisterCommand implements Command {
    User user = new User();

    /**
     * registers user
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        if (userService.checkRegisterImg(registerFormCommand.getImgNameId(), request.getParameter(LOGIMG))
                    && (request.getParameter(PASSWORD).equals(request.getParameter(PASSWORD_REP)))
                    && (userService.checkRegFields(Command.decodeParameter(request.getParameter(FIRST_NAME))
                        , Command.decodeParameter(request.getParameter(LAST_NAME))
                            , Command.decodeParameter(request.getParameter(FATHER_NAME))
                                , request.getParameter(EMAIL), request.getParameter(PASSWORD)))){
            if (userService.loginIsFree(request.getParameter(EMAIL))){
                request.setAttribute(FIELD_FAIL_INFO, adminPageCommand.getResourceBundle(locale, EMAIL_IS_ALREADY_REGISTERED));
                return registerFormCommand.execute(request, response);
            } else {
                user = new User(Command.decodeParameter(request.getParameter(FIRST_NAME)), Command.decodeParameter(request.getParameter(FATHER_NAME))
                            , Command.decodeParameter(request.getParameter(LAST_NAME))
                            , request.getParameter(EMAIL), (request.getParameter(PASSWORD) + PASSWORD_SALT).hashCode());
                userService.createUser(user);
                request.setAttribute(FIELD_SUC_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_USER)
                        + Command.decodeParameter(request.getParameter(FIRST_NAME)) 
                            + adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REG_SUCK_MESSAGE));
                return homePageCommand.execute(request, response);
            }
        } else {
            request.setAttribute(FIELD_FAIL_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_VALUES_NOT_CORRECT));
            return registerFormCommand.execute(request, response);
        }
    }
}
