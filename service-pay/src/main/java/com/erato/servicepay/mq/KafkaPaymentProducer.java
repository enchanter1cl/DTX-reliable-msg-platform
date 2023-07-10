package com.erato.servicepay.mq;

//@Service
//@Slf4j
//public class KafkaPaymentProducer {
//
//    @Autowired
//    KafkaTemplate<String, String> kafkaTemplate;
//
//    /**
//     * 发送消息通知
//     * @param topic
//     * @param event
//     */
//    public void send(String topic, EventPay event) {
//
//        String eventPayStr = JSON.toJSONString(event);
//
//        ProducerRecord<String, String> record = new ProducerRecord<>(topic, eventPayStr);
//        kafkaTemplate.send(record);
//    }
//}
