package service;

import dao.IUserDao;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Singleton
public class UserService implements Serializable, IUserService {

    @EJB private IUserDao userDao;

    @PostConstruct public void init() {

    }

    public void addUser(User user) {
        user.setPassword(this.MD5(user.getPassword()));
        userDao.addUser(user);
    }

    public boolean verifUser(User user) {
        user.setPassword(this.MD5(user.getPassword()));
        return userDao.verifyUser(user);
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
