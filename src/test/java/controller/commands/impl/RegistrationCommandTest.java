//package controller.commands.impl;
//
//import controller.commands.CommandFactory;
//import model.entity.User;
//import model.service.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import util.Constants.*;
//
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RegistrationCommandTest {
//    @Mock
//    private HttpServletRequest request;
//    @Mock
//    private HttpServletResponse response;
//    @Mock
//    private HttpSession session;
//    @Mock
//    private User user;
//    @Mock
//    private UserService userService;
//    @InjectMocks
//    private SignInCommand signInCommand = new SignInCommand();
//
//    @Test
//    public void correctSignIn() {
//        String email = "admin@admin.com";
//        String password = "admin-password";
//
//        when(request.getParameter(email)).thenReturn(email);
//        when(request.getParameter(password)).thenReturn(password);
//        when(request.getSession()).thenReturn(session);
//
//        User user = new User();
//
//        when(user.getEmail()).thenReturn(email);
//        when(user.getPassword()).thenReturn(password);
//
//        doNothing().when(userService).singUp(any());
//
//        String path = signInCommand.execute(request, response);
//        assertEquals("redirect:" + CommandFactory.getCommand("login"), path);
//    }
//}