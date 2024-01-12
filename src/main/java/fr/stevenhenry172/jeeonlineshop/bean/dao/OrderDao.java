package fr.stevenhenry172.jeeonlineshop.bean.dao;

import fr.stevenhenry172.jeeonlineshop.entities.Order;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@LocalBean
public class OrderDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public void delete(Order order){
        em.remove(order);
    }

    public void update(Order order) {
        em.merge(order);
    }
}
