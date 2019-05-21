package model.entity;

import java.util.Date;
import java.util.Objects;

public class Bill {
    private Integer id;
    private String totalCost;
    private Date dates;
    private String status;
    private Invoice invoiceId;

    public Bill() {}

    public Bill(Integer id, String totalCost, Date dates, String status, Invoice invoiceId) {
        this.id = id;
        this.totalCost = totalCost;
        this.dates = dates;
        this.status = status;
        this.invoiceId = invoiceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        return id.equals(bill.id) &&
                totalCost.equals(bill.totalCost) &&
                dates.equals(bill.dates) &&
                status.equals(bill.status) &&
                invoiceId.equals(bill.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCost, dates, status, invoiceId);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", totalCost='" + totalCost + '\'' +
                ", dates=" + dates +
                ", status='" + status + '\'' +
                ", invoiceId=" + invoiceId +
                '}';
    }
}
