package com.erato.servicereliablemsg.service.impl;

import com.erato.servicereliablemsg.dao.PayEventDao;
import com.erato.servicereliablemsg.entity.PayEvent;
import com.erato.servicereliablemsg.mq.PayEventProducer;
import com.erato.servicereliablemsg.service.PayEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (PayEvent)表服务实现类
 *
 * @author makejava
 * @since 2023-07-09 18:22:19
 */
@Service("payEventService")
public class PayEventServiceImpl implements PayEventService {
    @Resource
    private PayEventDao payEventDao;

    @Autowired
    private PayEventProducer payEventProducer;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PayEvent queryById(Long id) {
        return this.payEventDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param payEvent    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<PayEvent> queryByPage(PayEvent payEvent, PageRequest pageRequest) {
        long total = this.payEventDao.count(payEvent);
        return new PageImpl<>(this.payEventDao.queryAllByLimit(payEvent, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param payEvent 实例对象
     * @return 实例对象
     */
    @Override
    public PayEvent insert(PayEvent payEvent) {
        this.payEventDao.insert(payEvent);
        return payEvent;
    }

    /**
     * 修改数据
     *
     * @param payEvent 实例对象
     * @return 实例对象
     */
    @Override
    public PayEvent update(PayEvent payEvent) {
        this.payEventDao.update(payEvent);

        if (payEvent.getStatus() == 1) {
            /* 发送给 mq， 状态为 1-已确认 */
            payEventProducer.send(payEvent);
            /* 改库 3-已发出 */
            payEvent.setStatus(3);
            int updateRows = payEventDao.update(payEvent);
            if (updateRows == 0) {
                return null;
            }
        }

        if (payEvent.getStatus() == 2) {
            /* 改库，2-已取消 */
            payEventDao.update(payEvent);
        }

        if (payEvent.getStatus() == 4) {
            /* 改库，4-已完成 */
            payEventDao.update(payEvent);
        }

        return this.queryById(payEvent.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.payEventDao.deleteById(id) > 0;
    }
}
