package com.erato.servicepay.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (PaymentStream)实体类
 *
 * @author makejava
 * @since 2023-07-05 14:19:10
 */
public class PaymentStream implements Serializable {
    private static final long serialVersionUID = 711307260832230896L;

    private Long id;

    private String paymentStreamNo;

    private String orderStreamNo;

    private Long userId;

    private Integer status;

    private Date createTime;

    private Date mgntTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentStreamNo() {
        return paymentStreamNo;
    }

    public void setPaymentStreamNo(String paymentStreamNo) {
        this.paymentStreamNo = paymentStreamNo;
    }

    public String getOrderStreamNo() {
        return orderStreamNo;
    }

    public void setOrderStreamNo(String orderStreamNo) {
        this.orderStreamNo = orderStreamNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMgntTime() {
        return mgntTime;
    }

    public void setMgntTime(Date mgntTime) {
        this.mgntTime = mgntTime;
    }

}

