package controller.commands.impl;


import controller.commands.Command;
import controller.commands.impl.util.CommandUtil;
import model.entity.User;
import model.entity.types.Role;
import org.apache.log4j.Logger;
import util.HashPassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand implements Command {
    private static final Logger logger = Logger.getLogger(UserCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String nameFromRequest = request.getParameter("name");
        String passFromRequest = request.getParameter("pass");
        logger.info("Attempt to log in user: " + nameFromRequest);

        User user = userService.login(nameFromRequest);
        String password = user.getPassword();


//        try {
        if (HashPassword.getPwdHash(passFromRequest).equals(password)) {
            request.getSession(true).setAttribute("User", user);
            logger.info("Successfully logged in user: " + user.getLogin());
            if (Role.CAHIER_CHIEF.getRole() == user.getRole()) {
                return "redirect:/" + CommandUtil.ADMIN_ROOM_LIST.getPath();
            }
            return "redirect:/" + CommandUtil.CAHIER_LIST.getPath();
        }
//        }catch (ServiceException e) {
//            request.setAttribute("wrongPassOrLogin", true);
//        }
        return SIGN_IN_JSP;
    }
}

