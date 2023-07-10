package com.erato.servicepay.service.impl;

import com.erato.commonmodule.dto.CommonResp;
import com.erato.commonmodule.dto.PayEvent;
import com.erato.servicepay.dao.PayOrderRelDao;
import com.erato.servicepay.dao.PaymentStreamDao;
import com.erato.servicepay.entity.PayOrderRel;
import com.erato.servicepay.entity.PaymentStream;
import com.erato.servicepay.feign.SvcReliableMsgClient;
import com.erato.servicepay.service.PayOrderRelService;
import com.erato.servicepay.service.PaymentStreamService;
import com.erato.servicepay.vo.PayCallbackReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (PaymentStream)表服务实现类
 *
 * @author makejava
 * @since 2023-07-05 14:19:11
 */
@Service("paymentStreamService")
@Slf4j
public class PaymentStreamServiceImpl implements PaymentStreamService {

    @Autowired
    private SvcReliableMsgClient svcReliableMsgClient;

    @Resource
    private PaymentStreamDao paymentStreamDao;

    @Autowired
    private PayOrderRelDao payOrderRelDao;

    @Autowired
    private PayOrderRelService payOrderRelService;


    public CommonResp<Integer> payEventBackQuery(Long orderId) {

        PayOrderRel qPayOrderRel = new PayOrderRel();
        qPayOrderRel.setOrderId(orderId);
        Long payId = payOrderRelDao.queryPayIdByOrderId(orderId);

        PaymentStream paymentStream = paymentStreamDao.queryById(payId);
        if (paymentStream == null) {
            /* 无此支付流水记录 */
            return CommonResp.fail(null);
        }
        Integer status = paymentStream.getStatus();
        return CommonResp.success(status);
    }

    /**
     * 收到了第三方支付的回调
     * @param payCallbackReq
     * @return
     */
    @Override
    public CommonResp payCallback(PayCallbackReq payCallbackReq) {

        /**
         * 回调结果为空或支付失败
         */
        if (payCallbackReq.getRes() != 1){
            return CommonResp.fail();
        }

        /**
         * 通知可靠消息服务
         */
        PayEvent payEvent = new PayEvent();
        payEvent.setStatus(0); //0-待确认
        payEvent.setPayload(payCallbackReq.getOrderId());
        ResponseEntity<PayEvent> tobeAckRes = svcReliableMsgClient.add(payEvent);
        PayEvent payEventDb = tobeAckRes.getBody();
        if (payEventDb == null) {
            return CommonResp.fail("通知【待确认】事件，失败");
        }

        /* 更新支付流水表 与 关系表 */
        PaymentStream paymentStream = new PaymentStream();
        paymentStream.setId(Long.valueOf(payCallbackReq.getPaymentId()));
        paymentStream.setStatus(1);
        PaymentStream paymentStreamDb = update(paymentStream);
        if (paymentStreamDb == null) return CommonResp.fail("update payment tbl fail");
        PayOrderRel payOrderRel = new PayOrderRel();
        payOrderRel.setPaymentId(Long.valueOf(payCallbackReq.getPaymentId()));
        payOrderRel.setOrderId(Long.valueOf(payCallbackReq.getOrderId()));
        PayOrderRel payOrderRelDb = payOrderRelService.update(payOrderRel);
        if (payOrderRelDb == null) return CommonResp.fail("update rel tbl fail");

        /* 通知可靠消息服务 */
        payEventDb.setStatus(1);
        ResponseEntity<PayEvent> editRes = svcReliableMsgClient.edit(payEventDb);

        return CommonResp.success();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PaymentStream queryById(Long id) {
        return this.paymentStreamDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param paymentStream 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<PaymentStream> queryByPage(PaymentStream paymentStream, PageRequest pageRequest) {
        long total = this.paymentStreamDao.count(paymentStream);
        return new PageImpl<>(this.paymentStreamDao.queryAllByLimit(paymentStream, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param paymentStream 实例对象
     * @return 实例对象
     */
    @Override
    public PaymentStream insert(PaymentStream paymentStream) {
        this.paymentStreamDao.insert(paymentStream);
        return paymentStream;
    }

    /**
     * 修改数据
     *
     * @param paymentStream 实例对象
     * @return 实例对象
     */
    @Override
    public PaymentStream update(PaymentStream paymentStream) {
        this.paymentStreamDao.update(paymentStream);
        return this.queryById(paymentStream.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.paymentStreamDao.deleteById(id) > 0;
    }
}
