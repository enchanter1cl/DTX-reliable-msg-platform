package com.erato.servicereliablemsg.dao;

import com.erato.servicereliablemsg.entity.PayEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (PayEvent)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-09 18:22:18
 */

@Mapper
public interface PayEventDao {

    /**
     * 不分页条件查询
     * @return
     */
    List<PayEvent> queryAllByConditions(PayEvent payEvent);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayEvent queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param payEvent 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<PayEvent> queryAllByLimit(PayEvent payEvent, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param payEvent 查询条件
     * @return 总行数
     */
    long count(PayEvent payEvent);

    /**
     * 新增数据
     *
     * @param payEvent 实例对象
     * @return 影响行数
     */
    int insert(PayEvent payEvent);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PayEvent> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PayEvent> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PayEvent> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PayEvent> entities);

    /**
     * 修改数据
     *
     * @param payEvent 实例对象
     * @return 影响行数
     */
    int update(PayEvent payEvent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

