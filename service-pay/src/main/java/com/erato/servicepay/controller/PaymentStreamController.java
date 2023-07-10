package com.erato.servicepay.controller;

import com.erato.commonmodule.dto.CommonResp;
import com.erato.servicepay.entity.PaymentStream;
import com.erato.servicepay.service.PaymentStreamService;
import com.erato.servicepay.vo.PayCallbackReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PaymentStream)表控制层
 *
 * @author makejava
 * @since 2023-07-05 14:19:09
 */
@RestController
@RequestMapping("paymentStream")
public class PaymentStreamController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentStreamService paymentStreamService;

    /**
     * 收到第三方支付传来的回调
     * @param payCallbackReq
     * @return
     */
    @PutMapping("/alipay")
    public CommonResp payCallback(@RequestBody PayCallbackReq payCallbackReq) {
        return paymentStreamService.payCallback(payCallbackReq);
    }

    /**
     * 收到可靠消息服务发来的回查请求 （PayEvent 的 payload 是 orderId）
     */
    @GetMapping("/backQuery/{orderId}")
    public CommonResp<Integer> payEventBackQuery(@PathVariable("orderId") Long orderId) {
        return paymentStreamService.payEventBackQuery(orderId);
    }

    /**
     * 分页查询
     *
     * @param paymentStream 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<PaymentStream>> queryByPage(PaymentStream paymentStream, PageRequest pageRequest) {
        return ResponseEntity.ok(this.paymentStreamService.queryByPage(paymentStream, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<PaymentStream> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.paymentStreamService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param paymentStream 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<PaymentStream> add(PaymentStream paymentStream) {
        return ResponseEntity.ok(this.paymentStreamService.insert(paymentStream));
    }

    /**
     * 编辑数据
     *
     * @param paymentStream 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<PaymentStream> edit(PaymentStream paymentStream) {
        return ResponseEntity.ok(this.paymentStreamService.update(paymentStream));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.paymentStreamService.deleteById(id));
    }

}

