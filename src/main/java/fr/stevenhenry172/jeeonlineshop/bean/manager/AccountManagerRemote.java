package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.ejb.Remote;

import java.rmi.RemoteException;
import java.util.Optional;

@Remote
public interface AccountManagerRemote {

    /**
     * This method allows to create a new user if its login is not already used
     * @param firstName New user first name
     * @param name New user last name
     * @param login New user login
     * @param password New user password
     * @return an Optional of the new UserAccount if the user is created, an empty Optional otherwise
     * @throws RemoteException if an error occurs with the connection
     */
    Optional<UserAccount> createAccount(String firstName, String name, String login, String password) throws RemoteException;

    /**
     * Attempts the login of an already registered user
     * @return an Optional of the UserAccount if the login and passowrd are correct, an empty Optional otherwise
     * @throws RemoteException if an error occurs with the connection
     */
    Optional<UserAccount> attemptLogin(String login, String password) throws RemoteException;
}
