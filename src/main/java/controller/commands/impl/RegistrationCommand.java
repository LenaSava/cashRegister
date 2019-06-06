package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.exception.ServiceException;
import model.exception.WrongDataException;
import model.service.factory.ServiceFactory;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String nameFromRequest = request.getParameter("name");
        String emailFromRequest = request.getParameter("email");
        String passFromRequest = request.getParameter("pass");


        logger.info("Get data from registration: " + nameFromRequest + passFromRequest + emailFromRequest);

        try {
            userService.registrationUser(nameFromRequest,  emailFromRequest, passFromRequest);
            return PageResourseManager.getProperty("home");
        } catch (RuntimeException e) {
            request.setAttribute("error", true);
            logger.info("Error in registration form" + e);
        }

        return PageResourseManager.getProperty("registration.page.jsp");
    }
}

