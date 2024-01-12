package fr.stevenhenry172.jeeonlineshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.io.Serial;

@Entity
public class Cart extends ProductSelection {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(optional = false)
    private UserAccount account;

    public Cart() {
        super();
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }
}
