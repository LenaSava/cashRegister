package controller.commands;

import controller.commands.impl.ErrorCommand;
import controller.commands.impl.RegistrationCommand;
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
//        commands.put(Util.LOGIN_PAGE.getPath(), new SignInCommand());
        commands.put(CommandUtil.REGISTRATION.getPath(), new RegistrationCommand());
//        commands.put(Util.USER_HOME_PAGE.getPath(), new UserHomePageCommand());
//        commands.put(Util.LOG_OUT.getPath(), new LogOutCommand());
//        commands.put(Util.ERROR_PAGE.getPath(), new ErrorCommand());
//        commands.put(Util.SIGN_IN.getPath(), new UserCommand());
//        commands.put(Util.SHOW_REGISTRATION.getPath(), new ShowRegistrationPageCommand());
//        commands.put(Util.LOG_OUT.getPath(), new LogOutCommand());
//        commands.put(Util.INFO_PAGE.getPath(), new InfoCommand());
//        commands.put(Util.ROOM_LIST.getPath(), new RoomListCommand());
//        commands.put(Util.ADMIN_ROOM_LIST.getPath(), new AdminRoomListCommand());
//        commands.put(Util.CREATE_ROOM.getPath(), new CreateRoomCommand());
//        commands.put(Util.SHOW_CREATE.getPath(), new ShowCreateRoomCommand());
    }

    public static Command getCommand(String url) {
        Command command = commands.getOrDefault(url, (r,response) ->"/index.jsp");
//        Command command = commands.get(url);
//        logger.info(String.format("get command by url = %s", url));

        if( command == null){
            return new ErrorCommand();
        }

        return command;

    }
}