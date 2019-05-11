package model.service;

import model.entity.User;
import model.exception.ServiceException;

public interface UserService {

    User login(String name);

    void singUp(User user);

    User registrationUser(String nickname, String password, String email) throws ServiceException;
}

