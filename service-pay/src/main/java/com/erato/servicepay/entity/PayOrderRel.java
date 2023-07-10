package com.erato.servicepay.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (PayOrderRel)实体类
 *
 * @author makejava
 * @since 2023-07-10 15:38:55
 */

@Data
public class PayOrderRel implements Serializable {
    private static final long serialVersionUID = 908509922452138036L;
    
    private Integer id;
    
    private Long paymentId;
    
    private Long orderId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}

