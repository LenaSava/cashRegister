package model.service;

import model.entity.User;
import model.exception.ServiceException;

/**
 * The root interface for user services
 *
 * @see model.service.impl.UserServiceImpl
 */

public interface UserService {


    User login(String name);
    /**
     * method that login user
     *
     */

    void singUp(User user);
    /**
     * method that create new user in DB
     *
     */

    User registrationUser(String nickname, String password, String email) throws ServiceException;
    /**
     * method that validate user
     *
     */
}

