package model.entity;

import java.util.Objects;

public class Product {
    private Integer id;
    private Integer code;
    private String name_En;
    private String name_ua;
    private Double cost;
    private Integer quantity;
    private Integer invoiceId;
    private Invoice invoice;

    public Product() {}

    public Product(Integer id, Integer code, String name_En, String name_ua, Double cost, Integer quantity) {
        this.id = id;
        this.code = code;
        this.name_En = name_En;
        this.name_ua = name_ua;
        this.cost = cost;
        this.quantity = quantity;

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getName() {
        return name_En;
    }
    public void setName(String name_En) {
        this.name_En = name_En;
    }
    public String getName_ua() {
        return name_ua;
    }
    public void setName_ua(String name_ua) {
        this.name_ua = name_ua;
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
        return name_En.equals(that.name_En);
//                quantity.equals(that.quantity) &&
//                cost.equals(that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name_En, quantity, cost);
    }

    @Override
    public String toString() {
        return "\nProduct{" +
                "id=" + id +
                "code=" + code +
                ", room type='" + name_En + '\'' +
                ", room type='" + name_ua + '\'' +
                ", cost='" + cost + '\'' +
                ", quota=" + quantity +
                '}';
    }

}

