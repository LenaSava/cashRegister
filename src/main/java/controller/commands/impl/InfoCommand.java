package controller.commands.impl;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return INFO_VIEW;
    }
}
