package dao;

import entities.User;

import javax.ejb.Local;

@Local
public interface IUserDao {

    void addUser(User user);

    boolean verifyUser(User user);
}
