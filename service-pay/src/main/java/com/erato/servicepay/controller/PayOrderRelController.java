package com.erato.servicepay.controller;

import com.erato.servicepay.entity.PayOrderRel;
import com.erato.servicepay.service.PayOrderRelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PayOrderRel)表控制层
 *
 * @author makejava
 * @since 2023-07-10 15:38:55
 */
@RestController
@RequestMapping("payOrderRel")
public class PayOrderRelController {
    /**
     * 服务对象
     */
    @Resource
    private PayOrderRelService payOrderRelService;

    /**
     * 分页查询
     *
     * @param payOrderRel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<PayOrderRel>> queryByPage(PayOrderRel payOrderRel, PageRequest pageRequest) {
        return ResponseEntity.ok(this.payOrderRelService.queryByPage(payOrderRel, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<PayOrderRel> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.payOrderRelService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param payOrderRel 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<PayOrderRel> add(PayOrderRel payOrderRel) {
        return ResponseEntity.ok(this.payOrderRelService.insert(payOrderRel));
    }

    /**
     * 编辑数据
     *
     * @param payOrderRel 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<PayOrderRel> edit(PayOrderRel payOrderRel) {
        return ResponseEntity.ok(this.payOrderRelService.update(payOrderRel));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.payOrderRelService.deleteById(id));
    }

}

