package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return PageResourseManager.getProperty("error.page");
    }
}
