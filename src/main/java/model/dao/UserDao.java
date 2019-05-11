package model.dao;

import model.entity.User;

public interface UserDao extends GenericDao<User> {

    User findByName(String name);
//    Optional<User> getUserByEmail(String email);
}
