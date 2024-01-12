package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.bean.dao.UserAccountDao;
import fr.stevenhenry172.jeeonlineshop.entity.Cart;
import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

@Stateless
@LocalBean
public class AccountManager {

    @EJB
    UserAccountDao dao;

    public UserAccount createAccount(String firstName, String name, String login, String password) {
        var account = new UserAccount();
        account.setName(name);
        account.setFirstName(firstName);
        account.setLogin(login);
        account.setPassword(password);
        account.setOrders(new ArrayList<>());
        account.setCart(new Cart());

        dao.save(account);
        return account;
    }

    public Optional<UserAccount> getUser(String login, String password) {
        return dao.getUser(login, password);
    }
}
