package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.ejb.Remote;

import java.rmi.RemoteException;

@Remote
public interface OrderManagerRemote {

    /**
     * Validates the cart and saves it as an order
     * @param account UserAccount instance used as an authentication key
     * @throws RemoteException
     */
    void validateCart(UserAccount account) throws RemoteException;
}
