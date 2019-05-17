package model.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Integer id;
    private Double cost;
    private Integer quantity;
    private Integer userId;
    private User user;

    private List<Product> products = new ArrayList<>();

    public Invoice() {}

    private Invoice(Integer id, Double cost, Integer quantity,Integer userId, User user) {
        this.id = id;
        this.cost = cost;
        this.quantity = quantity;
        this.userId = userId;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void settProducts(List<Product> products) {
        this.products = products;
    }
}
