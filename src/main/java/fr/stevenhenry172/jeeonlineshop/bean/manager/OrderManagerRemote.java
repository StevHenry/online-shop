package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.entity.Cart;
import jakarta.ejb.Remote;

import java.rmi.RemoteException;

@Remote
public interface OrderManagerRemote {
    void validateCart(Cart cart) throws RemoteException;
}
