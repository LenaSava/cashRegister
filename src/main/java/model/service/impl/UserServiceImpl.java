package model.service.impl;

import model.dao.factory.DaoFactory;
import model.dao.UserDao;
import model.entity.User;
import model.exception.ServiceException;
import model.service.UserService;
import model.service.impl.util.Validation;
import util.HashPassword;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao =  daoFactory.createUserDao();

    public UserServiceImpl() {
    }

    public User login(String name){
        if (Validation.isValidParam(name)){
            User user = userDao.findByName(name);
            return user;
        } else {
            throw new ServiceException("format exception");
        }

    }

    public void singUp(final User user) {
        try {
            userDao.create(user);
        } catch (SQLException e) {
            //save error message into log
            throw new RuntimeException(e);
        }
    }

    public User registrationUser(String nickname, String password, String email) throws ServiceException {
        if (Validation.isValidParam(nickname) && Validation.isValidParam(password)) {

            User user = new User();

            user.setLogin(nickname);
            String passWithHash = HashPassword.getPwdHash(password);
            user.setPassword(passWithHash);
            user.setEmail(email);
            user.setRole(2);

            singUp(user);

            return user;
        } else {
            throw new ServiceException("format exception");
        }
    }
}

