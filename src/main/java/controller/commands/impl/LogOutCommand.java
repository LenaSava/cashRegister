package controller.commands.impl;

import controller.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    private static final Logger logger = Logger.getLogger(LogOutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        logger.info("Exit from your acc");

        return HOME;
    }
}