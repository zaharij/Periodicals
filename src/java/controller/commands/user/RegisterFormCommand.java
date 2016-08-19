/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static controller.constants.ConstantsController.*;
import java.util.Locale;

/**
 *RegisterFormCommand
 * @author Zakhar
 */
public class RegisterFormCommand implements Command {
    private static String imgNameId;

    /**
     * returns register form
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
        imgNameId = userService.getRandomLogImgName();
        request.setAttribute(LOGIMG, getImgNameId() + JPG_FORMAT);
        request.setAttribute(FIRST_NAME_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_FIRST_NAME));
        request.setAttribute(MIDDLE_NAME_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_MIDDLE_NAME));
        request.setAttribute(LAST_NAME_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LAST_NAME));
        request.setAttribute(EMAIL_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_EMAIL_REG));
        request.setAttribute(PASSWORD_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PASSWORD_REG));
        request.setAttribute(REPEAT_PASSWORD_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPEAT_PASS_REG));
        request.setAttribute(ENTER_FROM_PIC_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ENTER_FROM_PIC));
        request.setAttribute(REQUIRED_FIELDS_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REQUIRED_FIELDS));
        request.setAttribute(REGISTER_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REGISTER_SUBMIT));
        request.setAttribute(MAIN_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_MAIN_SUBMIT));
        return REGISTER_JSP;
    }

    /**
     * @return the imgNameId
     */
    public String getImgNameId() {        
        return imgNameId;
    }
}
