package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.ejb.Remote;

import java.rmi.RemoteException;
import java.util.Optional;

@Remote
public interface AccountManagerRemote {

    UserAccount createAccount(String firstName, String name, String login, String password) throws RemoteException;

    Optional<UserAccount> attemptLogin(String login, String password) throws RemoteException;
}
