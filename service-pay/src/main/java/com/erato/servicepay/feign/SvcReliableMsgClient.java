package com.erato.servicepay.feign;

import com.erato.commonmodule.dto.PayEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient("service-reliable-msg")
public interface SvcReliableMsgClient {

    @PostMapping("/payEvent")
    ResponseEntity<PayEvent> add(@RequestBody PayEvent payEvent);

    @PutMapping("/payEvent")
    ResponseEntity<PayEvent> edit(@RequestBody PayEvent payEvent);
}
