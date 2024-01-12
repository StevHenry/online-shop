package fr.stevenhenry172.jeeonlineshop.bean.dao;

import fr.stevenhenry172.jeeonlineshop.entities.Product;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class ProductDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Product product) {
        em.persist(product);
    }

    public void delete(Product product) {
        em.remove(product);
    }

    public void update(Product product) {
        em.merge(product);
    }

    public List<Product> getAll(){
        TypedQuery<Product> q = em.createQuery("select product from Product product", Product.class);
        return q.getResultList();
    }
}
