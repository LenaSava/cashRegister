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
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(code, product.code) &&
                Objects.equals(name_En, product.name_En) &&
                Objects.equals(name_ua, product.name_ua) &&
                Objects.equals(cost, product.cost) &&
                Objects.equals(quantity, product.quantity) &&
                Objects.equals(invoiceId, product.invoiceId) &&
                Objects.equals(invoice, product.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name_En, name_ua, cost, quantity, invoiceId, invoice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code=" + code +
                ", name_En='" + name_En + '\'' +
                ", name_ua='" + name_ua + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", invoiceId=" + invoiceId +
                ", invoice=" + invoice +
                '}';
    }
}

