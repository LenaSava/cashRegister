package model.entity;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private Double cost;
    private Double quantity;
//    private Invoice invoice;

    public Product() {}

    public Product(Integer id, String name, Double cost, Double quantity) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return name;
    }
    public void setProductName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return name.equals(that.name);
//                quantity.equals(that.quantity) &&
//                cost.equals(that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, cost);
    }

    @Override
    public String toString() {
        return "\nRoom{" +
                "id=" + id +
                ", room type='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", quota=" + quantity +
                '}';
    }

}

