package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.service.ServiceFactory;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);
    UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String nameFromRequest = request.getParameter("name");
        String passFromRequest = request.getParameter("pass");
        String emailFromRequest = request.getParameter("email");

        logger.info("Get data from registration: " + nameFromRequest + passFromRequest + emailFromRequest);

        try {
            userService.registrationUser(nameFromRequest, passFromRequest, emailFromRequest);
        } catch (RuntimeException e) {
            request.setAttribute("error", true);
            logger.info("Error in registration form" + e);
        }

        return PageResourseManager.getProperty("home");
    }
}

