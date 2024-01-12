package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.bean.dao.UserAccountDao;
import fr.stevenhenry172.jeeonlineshop.entities.Cart;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

@Stateless
@LocalBean
public class AccountManager implements AccountManagerRemote {

    @EJB
    UserAccountDao dao;

    public Optional<UserAccount> createAccount(String firstName, String name, String login, String password) {
        if (dao.isUsernameAvailable(login)) {
            var account = new UserAccount();
            account.setName(name);
            account.setFirstName(firstName);
            account.setLogin(login);
            account.setPassword(password);
            account.setOrders(new ArrayList<>());
            account.setCart(new Cart());

            dao.save(account);
            return Optional.of(account);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserAccount> attemptLogin(String login, String password) {
        return dao.getUser(login, password);
    }
}
