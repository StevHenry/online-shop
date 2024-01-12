package fr.stevenhenry172.jeeonlineshop.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
public class UserAccount implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String name;

    @Id
    private String login;
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "account",  fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "account", orphanRemoval = true, optional = false)
    private Cart cart;

    public UserAccount(){
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
