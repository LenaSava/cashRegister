//package model.service.impl;
//
//import model.dao.factory.DaoFactory;
//import model.dao.impl.JDBCUserDao;
//import model.entity.User;
//import model.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//public class UserServiceImplTest {
//    @InjectMocks
//    UserService userService;
//    User user;
//    List<User> list;
//
//    @Mock
//    JDBCUserDao jdbcUserImpl;
//
//    @Mock
//    DaoFactory daoFactory;
//
//    @Before
//    public void setUp() throws Exception {
//
//        MockitoAnnotations.initMocks(this);
//
//        list= new ArrayList();
//        user.setLogin(Э);
//        user.setEmail(("test@mail.ru"));
//        user.get().setPassword("password");
//        list.add(user.get());
//
//        when(daoFactory.createUserDao()).thenReturn(jdbcUserImpl);
//
//        when(jdbcUserImpl.findByName("Steve")).thenReturn(user);
//        when(jdbcUserImpl.findAll()).thenReturn(list);
//
//
//    }
//
//    @Test
//    public void login() {
//    }
//
//    @Test
//    public void singUp() {
//    }
//
//    @Test
//    public void registrationUser() {
//    }
//}