package com.erato.serviceorder1.mq;

import com.alibaba.fastjson2.JSON;
import com.erato.commonmodule.dto.PayEvent;
import com.erato.serviceorder1.dao.OrderDao;
import com.erato.serviceorder1.entity.Order;
import com.erato.serviceorder1.feign.SvcReliableMsgClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "pay_success_reliable", groupId = "pay_success_reliable")
@Slf4j
public class KafkaPayEventConsumer {

    @Autowired
    OrderDao orderDao;

    @Autowired
    SvcReliableMsgClient svcReliableMsgClient;

    @KafkaHandler(isDefault = true)
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack){

        boolean orderRes = false;
        PayEvent payEvent = null;
        try {
            System.out.printf("topic is %s, offset is %d, partition is %s, value is %s \n", record.topic(), record.offset(),record.partition(), record.value());
            String valueStr = (String)record.value();
            payEvent = JSON.parseObject(valueStr, PayEvent.class);
            log.info("消费者监听到了 payEvent: {}", payEvent);
            orderRes = doOnMessage(payEvent);

        }catch (Exception e){
            e.printStackTrace();
        }

        if (orderRes) {
            // 手动提交offset
            ack.acknowledge();
            //通知可靠消息平台，更改 payEvent 状态 4-已完成
            payEvent.setStatus(4);
            svcReliableMsgClient.edit(payEvent);
        }

    }

    private <T> boolean doOnMessage(T t) {

        log.debug("not a repeated message, handle message: {}", t);
        log.info("bean: {}", t);

        /* 执行本地业务：修改订单状态 2-已支付 */
        PayEvent payEvent = (PayEvent) t;
        Long orderId = Long.valueOf(payEvent.getPayload());
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(2);
        int updateRows = orderDao.update(order);

        return updateRows > 0;
    }
}
