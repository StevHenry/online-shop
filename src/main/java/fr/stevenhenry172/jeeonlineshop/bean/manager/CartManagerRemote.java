package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.entity.Article;
import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.ejb.Remote;

import java.rmi.RemoteException;

@Remote
public interface CartManagerRemote {

    boolean addArticle(UserAccount account, Article<?> article) throws RemoteException;

    boolean removeArticle(UserAccount account, Article<?> article) throws RemoteException;
}
