package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.bean.dao.Dao;
import fr.stevenhenry172.jeeonlineshop.bean.dao.UserAccountDao;
import fr.stevenhenry172.jeeonlineshop.entity.Cart;
import fr.stevenhenry172.jeeonlineshop.entity.Order;
import fr.stevenhenry172.jeeonlineshop.entity.UserAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

@Stateless
@LocalBean
public class OrderManager implements OrderManagerRemote {

    @EJB
    Dao<Cart> cartDao;

    @EJB
    Dao<Order> orderDao;

    @EJB
    UserAccountDao userDao;

    @Override
    public void validateCart(UserAccount account) throws RemoteException {
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