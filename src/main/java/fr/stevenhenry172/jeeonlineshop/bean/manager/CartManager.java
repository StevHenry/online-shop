package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.bean.dao.Dao;
import fr.stevenhenry172.jeeonlineshop.entity.Article;
import fr.stevenhenry172.jeeonlineshop.entity.Cart;
import fr.stevenhenry172.jeeonlineshop.entity.Product;
import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;

@Stateless
@LocalBean
public class CartManager {

    @EJB
    Dao<Cart> cartDao;

    @EJB
    Dao<Product> productDao;

    @EJB
    Dao<Article<?>> articleDao;

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
