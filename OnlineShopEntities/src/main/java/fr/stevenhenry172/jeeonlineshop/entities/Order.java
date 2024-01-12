package fr.stevenhenry172.jeeonlineshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity
public class Order extends ProductSelection {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    private UserAccount account;

    private LocalDateTime paymentDate;

    public Order() {
        super();
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
