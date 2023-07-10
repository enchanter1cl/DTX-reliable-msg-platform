package com.erato.servicepay.vo;

import lombok.Data;

@Data
public class PayCallbackReq {


    String orderId;
    String paymentId;
    int res;
}
