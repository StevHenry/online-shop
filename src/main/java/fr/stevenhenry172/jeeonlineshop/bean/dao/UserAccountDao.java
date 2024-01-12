package fr.stevenhenry172.jeeonlineshop.bean.dao;

import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

@Stateless
@LocalBean
public class UserAccountDao extends CartDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * Gets a User from the database by its login and password
     *
     * @return an Optional of UserAccount if the login and password pair is correct
     */
    public Optional<UserAccount> getUser(String login, String password) {
        TypedQuery<UserAccount> q = em.createQuery("select user from UserAccount user where user.login = :loginVal and user.password = :passVal", UserAccount.class);
        q.setParameter("loginVal", login);
        q.setParameter("passVal", password);

        if (q.getResultList().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(q.getResultList().get(0));
    }

    public boolean isUsernameAvailable(String username) {
        TypedQuery<UserAccount> q = em.createQuery("select user from UserAccount user where user.login = :loginVal", UserAccount.class);
        q.setParameter("loginVal", username);
        return q.getResultList().isEmpty();
    }

    public void save(UserAccount account) {
        em.persist(account);
    }

    public void delete(UserAccount account) {
        em.remove(account);
    }

    public void update(UserAccount account) {
        em.merge(account);
    }
}
