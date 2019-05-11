package controller.commands;

import model.service.ServiceFactory;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    String SIGN_IN_JSP = "/WEB-INF/views/login.jsp";
    String ROOM_LIST_JSP = "/WEB-INF/views/roomList.jsp";
    String ADMIN_HOME_JSP = "/WEB-INF/views/roomListAdmin.jsp";
    String REGISTRATION_PAGE_JSP = "/WEB-INF/views/registration.jsp";
    String ERROR_PAGE = "/WEB-INF/views/error.jsp";
    String HOME = "/index.jsp";
    String INFO_VIEW = "/WEB-INF/views/infoView.jsp";
    String USER = "/WEB-INF/views/userHomePage.jsp";
    String CREATE_ROOM = "/WEB-INF/views/createRoom.jsp";


//    RoomService roomService = ServiceFactory.getRoomService();
    UserService userService = ServiceFactory.getUserService();

    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
