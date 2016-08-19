/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entities.Article;
import model.service.constants.ConfigLog;
import model.service.constants.LogSettings;
import org.apache.log4j.Logger;

/**
 *WriteArticleCommand
 * @author Zakhar
 */
public class WriteArticleCommand implements Command{
    private Article article = new Article();
    private static Logger logger = Logger.getLogger(WriteArticleCommand.class);
    
    /**
     * SETS NEW ARTICLE
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);        
        article.setPeriodical((String) sessionUser.getAttribute(SUBJECT_TO_WRITE));      
        article.setTitle(Command.decodeParameter(request.getParameter(ARTICLE_NAME)).trim());
        article.setAuthorsEmail((String) sessionUser.getAttribute(EMAIL));
        article.setArticleAnnotation(Command.decodeParameter(request.getParameter(ANNOTATION)).trim());
        article.setArticleText(Command.decodeParameter(request.getParameter(ARTICLE_TEXT)).trim());
        article.setPermisssion((boolean) sessionUser.getAttribute(IS_WRITER)? 1: 0);
        articlesService.setArticleToDB(article);
        ConfigLog configLog = new ConfigLog(LogSettings.LOG_PROPERTIES_FILE);
        configLog.init();
        logger.info(NEW_ARTICLE_CREATED_MESSAGE + article.getTitle());
        return userPageCommand.execute(request, response);
    }
}
