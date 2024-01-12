package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.bean.dao.CartDao;
import fr.stevenhenry172.jeeonlineshop.bean.dao.ProductDao;
import fr.stevenhenry172.jeeonlineshop.entities.Article;
import fr.stevenhenry172.jeeonlineshop.entities.Product;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;

@Stateless
@LocalBean
public class CartManager implements CartManagerRemote {

    @EJB
    CartDao cartDao;

    @EJB
    ProductDao productDao;

    public boolean addArticle(UserAccount account, Article<?> article) {
        Product product = article.getProduct();
        if (product.getStock() > article.getQuantity()) {
            account.getCart().getArticles().add(article);
            product.setStock(product.getStock() - article.getQuantity());
            cartDao.update(account.getCart());
            productDao.update(product);
            return true;
        }
        return false;
    }

    public boolean removeArticle(UserAccount account, Article<?> article) {
        Product product = article.getProduct();
        if (account.getCart().getArticles().contains(article)) {
            account.getCart().getArticles().remove(article);
            product.setStock(product.getStock() + article.getQuantity());
            cartDao.update(account.getCart());
            productDao.update(product);
            return true;
        }
        return false;
    }
}
