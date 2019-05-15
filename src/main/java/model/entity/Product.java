package model.entity;

import java.util.Objects;

public class Product {
    private Integer id;
    private Integer code;
    private String name;
    private Double cost;
    private Double quantity;
    private Integer invoiceId;
    private Invoice invoice;

    public Product() {}

    public Product(Integer id, Integer code, String name, Double cost, Double quantity) {
        this.id = id;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
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
    public Integer getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

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
                "code=" + code +
                ", room type='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", quota=" + quantity +
                '}';
    }

}

