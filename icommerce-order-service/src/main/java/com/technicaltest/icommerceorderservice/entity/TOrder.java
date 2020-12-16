package com.technicaltest.icommerceorderservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class TOrder {
//    @Id
//    @GeneratedValue(generator = "hibernate-uuid")
//    @GenericGenerator(name = "hibernate-uuid", strategy = "hibernate-uuid")
//    @Column(name = "uuid", unique = true)
//    private String orderUUID;
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String orderID;
    @Column
    private Date startedDate;
    @Column
    private Date modifiedDate;

    public TOrder() {
        super();

    }

    public TOrder(String orderID, Date startedDate, Date modifiedDate) {
        super();
        this.orderID = orderID;
        this.startedDate = startedDate;
        this.modifiedDate = modifiedDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
