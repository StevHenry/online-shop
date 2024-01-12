package fr.stevenhenry172.jeeonlineshop.bean.manager;

import fr.stevenhenry172.jeeonlineshop.bean.dao.ProductDao;
import fr.stevenhenry172.jeeonlineshop.entities.Product;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;
import java.util.List;

@Stateless
@LocalBean
public class ProductManager implements ProductManagerRemote {

    @EJB
    ProductDao productDao;

    public List<Product> getAllProducts(UserAccount account) {
        return productDao.getAll();
    }
}
