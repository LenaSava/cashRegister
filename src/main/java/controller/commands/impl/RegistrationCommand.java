package controller.commands.impl;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String nameFromRequest = request.getParameter("name");
        String passFromRequest = request.getParameter("pass");
        String emailFromRequest = request.getParameter("email");

        try {
            userService.registrationUser(nameFromRequest, passFromRequest, emailFromRequest);
        } catch (RuntimeException e) {
            request.setAttribute("error", true);
        }

        return HOME;
    }
}

