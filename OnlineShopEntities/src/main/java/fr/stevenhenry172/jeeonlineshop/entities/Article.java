package fr.stevenhenry172.jeeonlineshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class Article<T extends Product> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String articleId;

    private T product;

    private double quantity;

    @ManyToOne(optional = true)
    private ProductSelection selection;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public T getProduct() {
        return product;
    }

    public void setProduct(T product) {
        this.product = product;
    }
}
