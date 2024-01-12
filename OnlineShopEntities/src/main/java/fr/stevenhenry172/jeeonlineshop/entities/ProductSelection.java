package fr.stevenhenry172.jeeonlineshop.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class ProductSelection implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "selection")
    public List<Article<?>> articles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Article<?>> getArticles() {
        return articles;
    }

    public void setArticles(List<Article<?>> articles) {
        this.articles = articles;
    }
}
