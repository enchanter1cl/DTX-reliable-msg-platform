package com.erato.servicepay.service;

import com.erato.servicepay.entity.PayOrderRel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (PayOrderRel)表服务接口
 *
 * @author makejava
 * @since 2023-07-10 15:38:55
 */
public interface PayOrderRelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayOrderRel queryById(Integer id);

    /**
     * 分页查询
     *
     * @param payOrderRel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<PayOrderRel> queryByPage(PayOrderRel payOrderRel, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param payOrderRel 实例对象
     * @return 实例对象
     */
    PayOrderRel insert(PayOrderRel payOrderRel);

    /**
     * 修改数据
     *
     * @param payOrderRel 实例对象
     * @return 实例对象
     */
    PayOrderRel update(PayOrderRel payOrderRel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
