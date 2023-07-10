package com.erato.servicereliablemsg.task;


import com.erato.commonmodule.dto.CommonResp;
import com.erato.servicereliablemsg.dao.PayEventDao;
import com.erato.servicereliablemsg.entity.PayEvent;
import com.erato.servicereliablemsg.feign.SvcPayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PayEventBackQueryTask {

    @Autowired
    PayEventDao payEventDao;

    @Autowired
    SvcPayClient svcPayClient;

    /**
     * 对长时间处于未确认状态的消息进行回查
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void backQuery() {
        PayEvent qPayEvent = new PayEvent();
        qPayEvent.setStatus(0);

        List<PayEvent> payEvents = payEventDao.queryAllByConditions(qPayEvent);
        List<PayEvent> toBeAckedPayEvents = payEvents.stream().filter(e -> {
            return e.getStatus() == 0;
        }).collect(Collectors.toList());
        log.info("待回查列表：{}", toBeAckedPayEvents);

        toBeAckedPayEvents.forEach(e -> {
            Long orderId = Long.valueOf(e.getPayload());
            CommonResp<Integer> resp = svcPayClient.queryBack(orderId);
            Integer payStatus = resp.getData();
            if (payStatus == null) {
                throw new RuntimeException("回查失败 无此记录");
            }
            if (payStatus == 0) { //状态仍然为未支付，说明service-pay执行本地业务失败了
                e.setStatus(2); //2-已取消
                payEventDao.update(e);
            } else { //状态为已支付
                e.setStatus(1); //1-已确认
                payEventDao.update(e);
            }
        });
    }

}
