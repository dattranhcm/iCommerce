package com.technicaltest.icommerceorderservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_order_items")
public class TOrderItems {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uuid")
    private TOrder order;

    @Column(name = "item_uuid")
    private String itemUuid;

    @Column(name = "item_price")
    private Integer itemPrice;

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

    public String getItemUuid() {
        return itemUuid;
    }

    public void setItemUuid(String itemUuid) {
        this.itemUuid = itemUuid;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
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
