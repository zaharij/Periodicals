/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filters;

import controller.PeriodicalsServlet;
import controller.command.factory.CommandFactory;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Zakhar
 */
public class PeriodicalsFilter implements Filter {
    private CommandFactory commandFactory = new CommandFactory();
    private PeriodicalsServlet periodicalsServlet = new PeriodicalsServlet();
    private SuperAdminFilter superAdminFilter = new SuperAdminFilter();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = null;
        session = httpRequest.getSession(false);
        superAdminFilter.setLocaleDefault(session);
        if (session != null){
            if (session.getAttribute(IS_LOGINED) != null && (boolean)session.getAttribute(IS_LOGINED)){
                chain.doFilter(request, response);
                return;
            } else {
                if (httpRequest.getParameterNames().nextElement().startsWith(LANGUAGE)){
                    periodicalsServlet.forward(commandFactory.getLanguage().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else if (httpRequest.getParameter(REGISTER_USER) != null){
                    periodicalsServlet.forward(commandFactory.getRegisterForm().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else if (httpRequest.getParameter(REGISTER_DO) != null){
                    periodicalsServlet.forward(commandFactory.getRegister().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else if (httpRequest.getParameterMap().containsKey(EMAIL) && httpRequest.getParameterMap().containsKey(PASSWORD)
                        && !httpRequest.getParameterMap().containsKey(START_PAGE)){
                    periodicalsServlet.forward(commandFactory.getLogin().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else {
                    periodicalsServlet.forward(commandFactory.getHomePage().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                }
            }
        } else {
            periodicalsServlet.forward(ERROR_PAGE, httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
