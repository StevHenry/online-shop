package fr.stevenhenry172.jeeonlineshop.bean.dao;

import fr.stevenhenry172.jeeonlineshop.entities.Cart;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CartDao {

    @PersistenceContext
    private EntityManager em;

    public CartDao(){

    }

    public void save(Cart cart){
        em.persist(cart);
    }

    public void delete(Cart cart){
        em.remove(cart);
    }

    public void update(Cart cart) {
        em.merge(cart);
    }
}
