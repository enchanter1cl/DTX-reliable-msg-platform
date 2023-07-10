package com.erato.servicepay.service;

import com.erato.commonmodule.dto.CommonResp;
import com.erato.servicepay.entity.PaymentStream;
import com.erato.servicepay.vo.PayCallbackReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (PaymentStream)表服务接口
 *
 * @author makejava
 * @since 2023-07-05 14:19:11
 */
public interface PaymentStreamService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PaymentStream queryById(Long id);

    /**
     * 分页查询
     *
     * @param paymentStream 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<PaymentStream> queryByPage(PaymentStream paymentStream, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param paymentStream 实例对象
     * @return 实例对象
     */
    PaymentStream insert(PaymentStream paymentStream);

    /**
     * 修改数据
     *
     * @param paymentStream 实例对象
     * @return 实例对象
     */
    PaymentStream update(PaymentStream paymentStream);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    CommonResp payCallback(PayCallbackReq payCallbackReq);

    CommonResp<Integer> payEventBackQuery(Long orderId);
}
