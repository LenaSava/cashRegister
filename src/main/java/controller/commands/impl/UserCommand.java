package controller.commands.impl;


import controller.commands.Command;
import controller.commands.impl.util.CommandUtil;
import controller.commands.impl.util.PageResourseManager;
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


        try {
            User user = userService.login(nameFromRequest);
            String password = user.getPassword();

            if (HashPassword.getPwdHash(passFromRequest).equals(password)) {
                request.getSession(true).setAttribute("User", user);
                logger.info("Successfully logged in user: " + user.getLogin());
                    if (Role.SENIOR_CASHIER.getRole() == user.getRole()) {
                        return "redirect:/" + CommandUtil.SENIOR_CASHIER_PAGE.getPath();
                    } else if (Role.MANAGER.getRole() == user.getRole()) {
                        return "redirect:/" + CommandUtil.MANAGER_PAGE.getPath();
                    }
                return "redirect:/" + CommandUtil.CASHIER_PAGE.getPath();
            }
        }catch (RuntimeException e) {
            request.setAttribute("wrongPassOrLogin", true);
        }
        return PageResourseManager.getProperty("sign.in.jsp");
    }
}

