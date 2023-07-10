package com.erato.servicereliablemsg.controller;

import com.erato.servicereliablemsg.entity.PayEvent;
import com.erato.servicereliablemsg.service.PayEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PayEvent)表控制层
 *
 * @author makejava
 * @since 2023-07-09 18:22:17
 */
@RestController
@RequestMapping("payEvent")
@Slf4j
public class PayEventController {
    /**
     * 服务对象
     */
    @Resource
    private PayEventService payEventService;

    /**
     * 分页查询
     *
     * @param payEvent    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<PayEvent>> queryByPage(PayEvent payEvent, PageRequest pageRequest) {
        return ResponseEntity.ok(this.payEventService.queryByPage(payEvent, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<PayEvent> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.payEventService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param payEvent 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<PayEvent> add(@RequestBody PayEvent payEvent) {
        return ResponseEntity.ok(this.payEventService.insert(payEvent));
    }

    /**
     * 编辑数据
     *
     * @param payEvent 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<PayEvent> edit(@RequestBody PayEvent payEvent) {
        log.info("更新状态: {}", payEvent);
        return ResponseEntity.ok(this.payEventService.update(payEvent));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.payEventService.deleteById(id));
    }

}

