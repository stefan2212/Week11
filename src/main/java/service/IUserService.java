package service;

import entities.User;
import javax.ejb.Local;


@Local public interface IUserService {

    void addUser(User user);

    boolean verifUser(User user);

}
