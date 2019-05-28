package controller.commands.impl;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class YReportCommand implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        return Y_REPORT ;
    }
}
