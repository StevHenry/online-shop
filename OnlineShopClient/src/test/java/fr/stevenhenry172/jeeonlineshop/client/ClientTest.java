package fr.stevenhenry172.jeeonlineshop.client;

import fr.stevenhenry172.jeeonlineshop.bean.manager.AccountManagerRemote;
import fr.stevenhenry172.jeeonlineshop.entities.UserAccount;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Hashtable;
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
                    context.lookup("ejb:JEE-OnlineShop/OnlineShop-EJB/AccountManager!fr.stevenhenry172.jeeonlineshop.bean.manager.AccountManagerRemote");
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

    @Test
    public void addArticleTest(){

    }

    @AfterAll
    public static void end() throws NamingException {
        context.close();
    }
}
