package dao;

import entities.User;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton public class UserDao implements IUserDao {

    @PersistenceContext(unitName = "postgres") private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public boolean verifyUser(User user) {
        Query query = entityManager.createQuery(
                "Select u from User u where " + "u.username = :username and " + " u.password = :password ");
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        return !query.getResultList().isEmpty();
    }

}
