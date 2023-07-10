package com.erato.serviceorder1.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (EventOrder)实体类
 *
 * @author makejava
 * @since 2023-07-05 19:46:42
 */
@Data
@ToString
public class EventOrder implements Serializable {
    private static final long serialVersionUID = 595824545929752116L;
    /**
     * 事件id
     */
    private Long id;
    /**
     * 事件内容
     */
    private String payload;
    /**
     * 事件生命周期
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

