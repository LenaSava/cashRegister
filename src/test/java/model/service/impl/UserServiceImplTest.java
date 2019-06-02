package model.service.impl;

import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.entity.User;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private static final Logger logger = Logger.getLogger(UserServiceImplTest.class);
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    @Mock
    private UserDao userDao;
    @Mock
    private DaoFactory daoFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setLogin("Olga");
        user.setEmail("olga@gmail.com");
        user.setPassword("202cb962ac59075b964b07152d234b70");
        user.setRole(2);
        when(daoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findById(0)).thenReturn(user);
        when(userDao.findAll()).thenReturn(Collections.singletonList(user));
    }

    @Test
    public void login() {
        User user = userService.login("Olga");
        assertEquals(user, user);
        logger.info("Test UserLogin");
    }

    @Test
    public void singUp() {
    }

    @Test
    public void registrationUser() {
        User user = userService.registrationUser("Olga", "123", "olga@gmail.com");
        assertEquals(user, user);
    }
}