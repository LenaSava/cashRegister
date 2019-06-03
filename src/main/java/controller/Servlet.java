package controller;

import controller.commands.Command;
import controller.commands.factory.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    public void init() {
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/" , "");
        Command command = CommandFactory.getCommand(path);
        String page = command.execute(request, response);
        if(page.contains("redirect")){
            response.sendRedirect(request.getContextPath() + page.replace("redirect", "/"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
