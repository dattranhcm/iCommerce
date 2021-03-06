package com.technicaltest.icommerceorderservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "t_order_items")
public class TOrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "order_uuid", referencedColumnName = "uuid", columnDefinition = "RAW(16)", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_uuid")
    @NotNull
    @JsonBackReference
    private TOrder order;

    @Column(name = "item_uuid")
    private UUID itemUuid;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @Column(name = "sub_order_amount")
    private Integer subOrderAmount;

    @Column(name = "sub_order_status")
    private String subOrderStatus;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TOrder getOrder() {
        return order;
    }

    public void setOrder(TOrder order) {
        this.order = order;
    }

    public UUID getItemUuid() {
        return itemUuid;
    }

    public void setItemUuid(UUID itemUuid) {
        this.itemUuid = itemUuid;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getSubOrderAmount() {
        return subOrderAmount;
    }

    public void setSubOrderAmount(Integer subOrderAmount) {
        this.subOrderAmount = subOrderAmount;
    }

    public String getSubOrderStatus() {
        return subOrderStatus;
    }

    public void setSubOrderStatus(String subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
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
}
