package com.erato.servicereliablemsg.feign;


import com.erato.commonmodule.dto.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-pay")
public interface SvcPayClient {

    /**
     * 回查接口
     * @param orderId
     * @return
     */
    @GetMapping("/paymentStream/backQuery/{orderId}")
    public CommonResp<Integer> queryBack(@PathVariable("orderId") Long orderId);
}
