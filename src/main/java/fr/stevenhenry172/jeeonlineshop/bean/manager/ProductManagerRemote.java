package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.entities.Product;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ProductManagerRemote {

    /**
     * @param account authentication key
     * @return the list of all registered products
     */
    List<Product> getAllProducts(UserAccount account);
}
