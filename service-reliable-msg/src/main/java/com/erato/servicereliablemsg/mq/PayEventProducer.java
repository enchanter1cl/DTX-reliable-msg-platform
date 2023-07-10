package com.erato.servicereliablemsg.mq;


import com.alibaba.fastjson2.JSON;
import com.erato.servicereliablemsg.entity.PayEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PayEventProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 发送到 mq, 状态为 1-已确认
     * @param payEvent
     */
    public void send(PayEvent payEvent) {

        String payEventStr = JSON.toJSONString(payEvent);

        kafkaTemplate.send("pay_success_reliable", payEventStr);
    }
}
