package model.entity;

import model.entity.enumeration.BillStatus;

import java.util.Date;
import java.util.Objects;

public class Bill {
    private Integer id;
    private Integer totalCost;
    private Date dates;
    private BillStatus status;
    private Integer userId;

    public Bill() {
    }

    public Bill(Integer id, Integer totalCost, Date dates, BillStatus status, Integer userId) {
        this.id = id;
        this.totalCost = totalCost;
        this.dates = dates;
        this.status = status;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id.equals(bill.id) &&
                totalCost.equals(bill.totalCost) &&
                dates.equals(bill.dates) &&
                status == bill.status &&
                userId.equals(bill.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCost, dates, status, userId);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", totalCost='" + totalCost + '\'' +
                ", dates=" + dates +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}