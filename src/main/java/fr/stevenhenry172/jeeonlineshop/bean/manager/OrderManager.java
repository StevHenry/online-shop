package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.bean.dao.CartDao;
import fr.stevenhenry172.jeeonlineshop.bean.dao.OrderDao;
import fr.stevenhenry172.jeeonlineshop.bean.dao.UserAccountDao;
import fr.stevenhenry172.jeeonlineshop.entities.Cart;
import fr.stevenhenry172.jeeonlineshop.entities.Order;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

@Stateless
@LocalBean
public class OrderManager implements OrderManagerRemote {

    @EJB
    CartDao cartDao;

    @EJB
    OrderDao orderDao;

    @EJB
    UserAccountDao userDao;

    public void validateCart(UserAccount account) {
        Cart cart = account.getCart();
        Order order = new Order();
        order.setArticles(cart.getArticles());
        order.setAccount(cart.getAccount());
        order.setPaymentDate(LocalDateTime.now());

        cart.getArticles().clear();
        cart.getAccount().getOrders().add(order);

        orderDao.save(order);
        cartDao.delete(cart);
        userDao.update(cart.getAccount());
    }
}