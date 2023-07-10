package com.erato.serviceorder1.task;


//@Component
//@Slf4j
//public class OrderStatusTask {
//
//    @Autowired
//    EventOrderDao  eventOrderDao;
//
//    @Autowired
//    OrderService orderService;
//
//    /**
//     * 扫描 状态为 已接收 的事件
//     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void scanEventOrder() {
//
//        EventOrder eventOrder = new EventOrder();
//        eventOrder.setStatus(2);
//
//        List<EventOrder> eventOrders = eventOrderDao.queryOnCondition(eventOrder);
//        log.info("查到了：{}", eventOrders);
//
//        eventOrders.stream().map(event -> {
//            /* 执行本地业务，更新订单支付状态 */
//            String orderId = event.getPayload();
//            orderService.orderPaid(orderId);
//
//            /* 事件消息状态改为 3-已处理 */
//            event.setStatus(3);
//            eventOrderDao.update(event);
//            return null;
//        }).collect(Collectors.toList());
//
//        log.info("本轮扫描事件处理完毕");
//    }
//}
