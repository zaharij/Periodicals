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
 *WriteArticlePageCommand
 * @author Zakhar
 */
public class WriteArticlePageCommand implements Command{

    /**
     * returns "write article" page
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
        if (request.getParameter(SUBJECT_TO_WRITE) != null){
            sessionUser.setAttribute(SUBJECT_TO_WRITE, Command.decodeParameter(request.getParameter(SUBJECT_TO_WRITE)));
        }
        nameStatusCommand.execute(request, response);
        request.setAttribute(TITLE_PERIODICAL, sessionUser.getAttribute(SUBJECT_TO_WRITE));
        request.setAttribute(SEND_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SEND_SUBMIT));
        request.setAttribute(LOGOUT_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute(BACK_SUBMIT, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BACK_SUBMIT));
        request.setAttribute(ANNOTATION_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ANNOTATION));
        request.setAttribute(TEXT_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_TEXT));
        request.setAttribute(TITLE_PAGE_INFO, adminPageCommand.getResourceBundle(locale, LOCALE_KEY_TITLE));
        return WRITEARTICLE_JSP;
    }
}
