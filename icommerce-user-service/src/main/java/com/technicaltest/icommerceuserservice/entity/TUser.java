package com.technicaltest.icommerceuserservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

@Entity
public class TUser {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "hibernate-uuid")
    @Column(name = "uuid", unique = true)
    private String customerId;

//    @Id
//    @GeneratedValue
//    private Long customerId;

    @Column
    private String fullName;

    @Column
    private String mobile;

    @Column
    private String address;

    @Column(nullable = false)
    @Email
    private String email;

    @Column
    private Date startedDate;

    @Column
    private Date modifiedDate;

    public TUser() {
        super();
    }

    public TUser(String fullName, String mobile, String address, String email, Date startedDate, Date modifiedDate) {
        super();
        this.fullName = fullName;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
        this.startedDate = startedDate;
        this.modifiedDate = modifiedDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
//    public Long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
