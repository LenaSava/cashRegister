package model.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice {
    private Integer id;
    private Integer product_id;
    private Double cost;
    private Integer quantity;
    private Integer userId;
    private Integer userRoleId;
    private Integer billId;

    private List<Product> products = new ArrayList<>();

    public Invoice() {}

    private Invoice(Integer id, Integer product_id, Double cost, Integer quantity,Integer userId, Integer userRoleId, Integer billId) {
        this.id = id;
        this.product_id = product_id;
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
    public Integer getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id.equals(invoice.id) &&
                product_id.equals(invoice.product_id) &&
                cost.equals(invoice.cost) &&
                quantity.equals(invoice.quantity) &&
                userId.equals(invoice.userId) &&
                userRoleId.equals(invoice.userRoleId) &&
                billId.equals(invoice.billId) &&
                products.equals(invoice.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_id, cost, quantity, userId, userRoleId, billId, products);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", userRoleId=" + userRoleId +
                ", billId=" + billId +
                ", products=" + products +
                '}';
    }
}

