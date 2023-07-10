package com.erato.commonmodule.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * (PayEvent)实体类
 *
 * @author makejava
 * @since 2023-07-09 01:06:50
 */

@Data
public class PayEvent implements Serializable {
    private static final long serialVersionUID = 617902643175376115L;

    private Long id;

    private String payload;
    /**
     * 0-待确认, 1-已确认, 2-已取消,
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

