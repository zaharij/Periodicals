/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command.factory;

import controller.commands.Command;
import controller.commands.user.*;
import controller.commands.superadmin.*;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    private Map<String, Command> commandMap = new HashMap<String, Command>()
    {{
        put(REGISTER_USER, new RegisterFormCommand());
        put(START_PAGE, new HomePageCommand());
        put(REGISTER_DO, new RegisterCommand());
        put(LOGIN_USER, new LoginCommand());
        put(LOGOUT, new LogoutCommand());
        put(MORE_ABOUT_PERIODICAL, new PeriodicalUnsignedCommand());
        put(GET_ACQUAINTED, new PeriodicalUnsignedCommand());
        put(OPEN_PERIODICAL, new PeriodicalPageCommand());
        put(OPEN_ARTICLE, new ArticlePageCommand());
        put(BACK_TO_USER, new UserPageCommand());
        put(BACK_TO_PERIODICALS, new PeriodicalPageCommand());
        put(REPLENISH_ACCOUNT, new ReplenishAccountPageCommand());
        put(REPLENISH, new ReplenishCommand());
        put(SIGNE_UP, new SignedUpPeriodicalCommand());
        put(USER, new HomePageCommand());
        put(CHOOSE_PERIODICAL_TO_WRITE_ARTICLE, new WriteArticlePageCommand());
        put(SEND_ARTICLE, new WriteArticleCommand());
        put(VIEW_UNCHECKED_ARTICLE, new UncheckedArticlePageCommand());
        put(PERMIT_ARTICLE_USER, new PermitArticleUserCommand());
        put(DELETE_UNPERMITED_ARTICLE_USER, new DeleteUnpermitedArticleUserCommand());
        put(LOGIN_ADMIN, new LoginSuperAdminCommand());
        put(LOGOUT_ADMIN, new LogoutSuperAdminCommand());
        put(CREATE_PERIODICAL, new SetPeriodicalCommand());
        put(SUPERADMIN, new LoginPageCommand());
        put(LANGUAGE, new LanguageCommand());
        put(DELETE_PERIODICAL, new DeletePeriodicalCommand());
        put(UNSET_ADMIN, new SetAdminFalseCommand());
        put(DELETE_USER, new DeleteUserCommand());
        put(SET_ADMIN, new SetAdminTrueCommand());        
        put(UNBLOCK_USER, new UnblockUserCommand());
        put(BLOCK_USER, new BlockUserCommand());
        put(DELETE_PERIODICAL_ARTICLE, new DeletePeriodicalArticlePageCommand());
        put(DELETE_ARTICLE, new DeleteArticleCommand());
        put(PERMIT_ARTICLE, new PermitArticleCommand());
        put(DELETE_UNPERMITED_ARTICLE, new DeleteUnpermitedArticleCommand());
        put(VIEW_ARTICLE, new ViewArticleCommand());
        put(PAY_FOR_ARTICLES, new PayForArticlesCommand());
        put(USER_MANAGEMENT, new UserManagementCommand());
        put(PERIODICALS_MANAGEMENT, new AdminPageCommand());
    }};

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
            if (commandMap.containsKey(entry.getKey())){
                return commandMap.get(entry.getKey());
            } else if (entry.getKey().startsWith(LANGUAGE_STR)){
                return commandMap.get(LANGUAGE_STR);
            }        
        }
        return null;
    }

    /**
     * @return the login
     */
    public Command getLogin() {
        return commandMap.get(LOGIN_USER);
    }

    /**
     * @return the homePage
     */
    public Command getHomePage() {
        return commandMap.get(START_PAGE);
    }

    /**
     * @return the language
     */
    public Command getLanguage() {
        return commandMap.get(LANGUAGE);
    }

    /**
     * @return the loginAdmin
     */
    public Command getLoginAdmin() {
        return commandMap.get(LOGIN_ADMIN);
    }

    /**
     * @return the loginPage
     */
    public Command getLoginPage() {
        return commandMap.get(SUPERADMIN);
    }

    /**
     * @return the registerForm
     */
    public Command getRegisterForm() {
        return commandMap.get(REGISTER_USER);
    }

    /**
     * @return the register
     */
    public Command getRegister() {
        return commandMap.get(REGISTER_DO);
    }
    
}
