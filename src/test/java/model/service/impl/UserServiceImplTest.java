package model.service.impl;

import model.entity.User;
import model.entity.types.Role;
import model.service.ServiceFactory;
import model.service.UserService;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.CORBA.UnknownUserException;

import static org.junit.Assert.assertTrue;


public class UserServiceImplTest {

    private static UserService userService;
    private static User testUser;
    private final static String CORRECT_USER_NAME = "Yaroslav";
    private final static String CORRECT_USER_PASSWORD = "yaroslav";
    private final static String TEST_USER_NAME = "Somebody";
    private final static String TEST_USER_PASSWORD = "Else";
    private final static String TEST_USER_EMAIL = TEST_USER_NAME + "@gmail.com";

    private static final Logger log = Logger.getLogger(UserServiceImplTest.class);

    @BeforeClass
    public static void init() throws UnknownUserException {
        log.info("Starting tests");
        userService = ServiceFactory.getUserService();
        testUser = createTestUser();
    }

    private static User createTestUser() {
        User user = new User(1, TEST_USER_NAME, TEST_USER_PASSWORD, "gmail", 1);
        user.setEmail(TEST_USER_NAME + "@gmail.com");
        user.setRole(1);
        return user;
    }

    @Test
    @Ignore
    public void testAddUser() throws InterruptedException {

    }

    @Test
    public void login() {
    }

    @Test
    public void singUp() {
    }

    @Test
    public void registrationUser() {
    }
}