package com.erato.servicepay.service.impl;

import com.erato.servicepay.dao.PayOrderRelDao;
import com.erato.servicepay.entity.PayOrderRel;
import com.erato.servicepay.service.PayOrderRelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (PayOrderRel)表服务实现类
 *
 * @author makejava
 * @since 2023-07-10 15:38:55
 */
@Service("payOrderRelService")
public class PayOrderRelServiceImpl implements PayOrderRelService {
    @Resource
    private PayOrderRelDao payOrderRelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PayOrderRel queryById(Integer id) {
        return this.payOrderRelDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param payOrderRel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<PayOrderRel> queryByPage(PayOrderRel payOrderRel, PageRequest pageRequest) {
        long total = this.payOrderRelDao.count(payOrderRel);
        return new PageImpl<>(this.payOrderRelDao.queryAllByLimit(payOrderRel, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param payOrderRel 实例对象
     * @return 实例对象
     */
    @Override
    public PayOrderRel insert(PayOrderRel payOrderRel) {
        this.payOrderRelDao.insert(payOrderRel);
        return payOrderRel;
    }

    /**
     * 修改数据
     *
     * @param payOrderRel 实例对象
     * @return 实例对象
     */
    @Override
    public PayOrderRel update(PayOrderRel payOrderRel) {
        this.payOrderRelDao.update(payOrderRel);
        return this.queryById(payOrderRel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.payOrderRelDao.deleteById(id) > 0;
    }
}
