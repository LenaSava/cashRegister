package controller.commands.impl;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ERROR_PAGE;
    }
}

