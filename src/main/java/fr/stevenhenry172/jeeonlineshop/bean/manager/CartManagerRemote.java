package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.entities.Article;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.Remote;

import java.rmi.RemoteException;

@Remote
public interface CartManagerRemote {

    /**
     * Adds an article into an account cart
     * @param account UserAccount instance used as an authentication key
     * @param article to add in the cart
     * @return whether the adding of the article is a success
     * @throws RemoteException if an error occurs with the connection
     */
    boolean addArticle(UserAccount account, Article<?> article);

    /**
     * Remove an article from an account cart
     * @param account UserAccount instance used as an authentication key
     * @param article to remove from the cart
     * @return whether the removal of the article is a success
     * @throws RemoteException if an error occurs with the connection
     */
    boolean removeArticle(UserAccount account, Article<?> article);
}
