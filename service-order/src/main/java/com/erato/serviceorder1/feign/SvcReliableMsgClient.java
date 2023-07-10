package com.erato.serviceorder1.feign;

import com.erato.commonmodule.dto.PayEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("service-reliable-msg")
public interface SvcReliableMsgClient {

    /**
     * 更新消息状态为 4-已完成
     * @param payEvent
     * @return
     */
    @PutMapping("/payEvent")
    ResponseEntity<PayEvent> edit(@RequestBody PayEvent payEvent);
}
