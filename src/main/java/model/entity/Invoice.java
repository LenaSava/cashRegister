package model.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice {
    private Integer id;
    private String product_name;
    private Double cost;
    private Integer quantity;
    private Integer userId;
    private Integer userRoleId;
    private Integer billId;

    private List<Product> products = new ArrayList<>();

    public Invoice() {}

    private Invoice(Integer id, String product_name, Double cost, Integer quantity,Integer userId, Integer userRoleId, Integer billId) {
        this.id = id;
        this.product_name = product_name;
        this.cost = cost;
        this.quantity = quantity;
        this.userId = userId;
        this.userRoleId = userRoleId;
        this.billId = billId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) &&
                Objects.equals(product_name, invoice.product_name) &&
                Objects.equals(cost, invoice.cost) &&
                Objects.equals(quantity, invoice.quantity) &&
                Objects.equals(userId, invoice.userId) &&
                Objects.equals(userRoleId, invoice.userRoleId) &&
                Objects.equals(billId, invoice.billId) &&
                Objects.equals(products, invoice.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_name, cost, quantity, userId, userRoleId, products);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", userRoleId=" + userRoleId +
                ", products=" + products +
                ", billId=" + billId +
                '}';
    }
}

