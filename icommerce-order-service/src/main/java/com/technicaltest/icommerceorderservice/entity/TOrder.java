package com.technicaltest.icommerceorderservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "t_order")
public class TOrder {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "RAW(16)")
    private UUID uuid;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "ship_address")
    private String shipAddress;

    @Column(name = "order_code", unique = true)
    private String orderCode;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = TOrderItems.class, mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TOrderItems> orderItems = new ArrayList<>();

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public TOrder() {
        super();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TOrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TOrderItems> orderItems) {
        this.orderItems = orderItems;
    }
}
