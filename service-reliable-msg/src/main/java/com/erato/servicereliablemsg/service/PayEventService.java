package com.erato.servicereliablemsg.service;

import com.erato.servicereliablemsg.entity.PayEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (PayEvent)表服务接口
 *
 * @author makejava
 * @since 2023-07-09 18:22:19
 */
public interface PayEventService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayEvent queryById(Long id);

    /**
     * 分页查询
     *
     * @param payEvent    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<PayEvent> queryByPage(PayEvent payEvent, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param payEvent 实例对象
     * @return 实例对象
     */
    PayEvent insert(PayEvent payEvent);

    /**
     * 修改数据
     *
     * @param payEvent 实例对象
     * @return 实例对象
     */
    PayEvent update(PayEvent payEvent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
