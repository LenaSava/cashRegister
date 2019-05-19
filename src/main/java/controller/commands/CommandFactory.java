package controller.commands;

import controller.commands.impl.*;
import controller.commands.impl.util.CommandUtil;
import model.service.ServiceFactory;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static volatile CommandFactory commandFactory;
//private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put(CommandUtil.LOGIN_PAGE.getPath(), new SignInCommand());
        commands.put(CommandUtil.REGISTRATION.getPath(), new RegistrationCommand());
        commands.put(CommandUtil.LOG_OUT.getPath(), new LogOutCommand());
        commands.put(CommandUtil.ERROR_PAGE.getPath(), new ErrorCommand());
        commands.put(CommandUtil.SIGN_IN.getPath(), new UserCommand());
        commands.put(CommandUtil.SHOW_REGISTRATION.getPath(), new ShowRegistrationPageCommand());
        commands.put(CommandUtil.INFO_PAGE.getPath(), new InfoCommand());
        commands.put(CommandUtil.SENIOR_CASHIER_PAGE.getPath(), new SeniorCashierPage());
        commands.put(CommandUtil.CREATE_UPDATE.getPath(), new CreateProductCommand());
        commands.put(CommandUtil.SHOW_CREATE.getPath(), new ShowCreateProductCommand());
        commands.put(CommandUtil.CAHIER_PAGE.getPath(), new CahierPageCommand());
        commands.put(CommandUtil.MANAGER_PAGE.getPath(), new ManagerPageCommand());
        commands.put(CommandUtil.DELETE.getPath(), new DeleteCommand());
    }

    public static Command getCommand(String url) {
        Command command = commands.getOrDefault(url, (r,response) ->"/index.jsp");

        if( command == null){
            return new ErrorCommand();
        }

        return command;

    }
}