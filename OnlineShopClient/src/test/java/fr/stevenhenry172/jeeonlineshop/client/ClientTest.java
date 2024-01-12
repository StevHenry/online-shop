package fr.stevenhenry172.jeeonlineshop.client;

import fr.stevenhenry172.jeeonlineshop.bean.manager.AccountManagerRemote;
import fr.stevenhenry172.jeeonlineshop.bean.manager.CartManagerRemote;
import fr.stevenhenry172.jeeonlineshop.bean.manager.OrderManagerRemote;
import fr.stevenhenry172.jeeonlineshop.bean.manager.ProductManagerRemote;
import fr.stevenhenry172.jeeonlineshop.entities.Article;
import fr.stevenhenry172.jeeonlineshop.entities.Product;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

public class ClientTest {

    private static Context context;
    private static UserAccount user;

    @BeforeAll
    public static void init() {
        Hashtable<Object, Object> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        //jndiProperties.put(Context.SECURITY_PRINCIPAL, "root");
        //jndiProperties.put(Context.SECURITY_CREDENTIALS, "root");
        try {
            context = new InitialContext(jndiProperties);
            AccountManagerRemote accountManager = (AccountManagerRemote)
                    context.lookup("ejb:JEE-OnlineShop/OnlineShop-EJB/AccountManagerRemote!fr.stevenhenry172.jeeonlineshop.bean.manager.AccountManagerRemote");
            Optional<UserAccount> userOpt = accountManager.createAccount("Steven", "HENRY", "shenry", "mySuperP@ssword");
            if (userOpt.isEmpty()) {
                user = accountManager.attemptLogin("shenry", "mySuperP@ssword")
                        .orElseThrow(() -> new IllegalArgumentException("Wrong id/password"));
            } else {
                user = userOpt.get();
            }
        } catch (NamingException | IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @AfterAll
    public static void end() throws NamingException {
        context.close();
    }

    @Test
    public void addRemoveArticleTest() throws NamingException {
        ProductManagerRemote pm = (ProductManagerRemote)
                context.lookup("ejb:JEE-OnlineShop/OnlineShop-EJB/ProductManagerRemote!fr.stevenhenry172.jeeonlineshop.bean.manager.ProductManagerRemote");
        List<Product> productList = pm.getAllProducts(user);
        if (!productList.isEmpty()) {
            CartManagerRemote cartManager = (CartManagerRemote)
                    context.lookup("ejb:JEE-OnlineShop/OnlineShop-EJB/CartManagerRemote!fr.stevenhenry172.jeeonlineshop.bean.manager.CartManagerRemote");
            Article<Product> article = new Article<>();
            article.setProduct(productList.get(0));
            article.setQuantity(2);
            if (productList.get(0).getStock() > 0) {
                Assertions.assertTrue(cartManager.addArticle(user, article));
                Assertions.assertTrue(cartManager.removeArticle(user, article));
            } else {
                Assertions.assertFalse(cartManager.addArticle(user, article));
                Assertions.assertFalse(cartManager.removeArticle(user, article));
            }
        }
    }

    @Test
    public void convertToOrderTest() throws NamingException {
        OrderManagerRemote orderManager = (OrderManagerRemote)
                context.lookup("ejb:JEE-OnlineShop/OnlineShop-EJB/OrderManagerRemote!fr.stevenhenry172.jeeonlineshop.bean.manager.OrderManagerRemote");
        orderManager.validateCart(user);
    }
}
