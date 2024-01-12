package fr.stevenhenry172.jeeonlineshop.bean.dao;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@LocalBean
public class Dao<T> {

    @PersistenceContext
    protected EntityManager em;

    public void save(T t){
        em.persist(t);
    }

    public void delete(T t){
        em.remove(t);
    }

    public void update(T t) {
        em.merge(t);
    }
}
