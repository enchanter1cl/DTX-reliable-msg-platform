package com.erato.serviceorder1.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Order)实体类
 *
 * @author makejava
 * @since 2023-07-05 14:31:03
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 619440102674893913L;

    private Long id;

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

