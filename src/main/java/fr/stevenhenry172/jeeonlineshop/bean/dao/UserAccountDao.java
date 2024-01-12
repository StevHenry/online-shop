package fr.stevenhenry172.jeeonlineshop.bean.dao;

import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class UserAccountDao extends Dao<UserAccount> {

    /**
     * Gets a User from the database by its login and password
     * @return an Optional of UserAccount if the login and password pair is correct
     */
    public Optional<UserAccount> getUser(String login, String password){
        TypedQuery<UserAccount> q = em.createQuery("select user from UserAccount user where user.login = :loginVal and user.password = :passVal", UserAccount.class);
        q.setParameter("loginVal", login);
        q.setParameter("passVal", password);

        if (q.getResultList().isEmpty()){
            return Optional.empty();
        }

        return Optional.of(q.getResultList().get(0));
    }

    public boolean isUsernameAvailable(String username){
        TypedQuery<UserAccount> q = em.createQuery("select user from UserAccount user where user.login = :loginVal", UserAccount.class);
        q.setParameter("loginVal", username);
        return q.getResultList().isEmpty();
    }
}
