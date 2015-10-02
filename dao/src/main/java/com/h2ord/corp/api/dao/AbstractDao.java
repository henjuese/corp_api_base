package com.h2ord.corp.api.dao;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by chy on 14-9-26.
 */
public abstract interface AbstractDao<E, I extends Serializable> {

    E saveOrUpdate(E paramE);

    E findById(I paramI);

    E loadById(I paramI);

    List<E> findAll();

    void delete(E paramE);

    /**
     * criterion搜索
     *
     * @param paramCriterion
     */
    List<E> findByCriteria(Criterion paramCriterion);

    /**
     * 分页查询(hql)
     *
     * @param hql          如：from 类.class.getName() as e where e.name like :search
     * @param conditionMap 查询条件，key为名称，value为值 如key="search" value="%aa%"
     */
    List<E> findByHql(String hql, int start, int limit, Map<String, Object> conditionMap);

    List<E> findByHql(String hql, Map<String, Object> conditionMap);


    List<E> findAllByPage(int start, int limit);

    /**
     * 查找总数
     *
     * @return
     */
    Long findAllCount();

    /**
     * 删除
     *
     * @param paramI
     */
    void delete(I paramI);

    /**
     * 查找总数(hql)
     *
     * @param countHql     如：select count(e.id) from 类.class.getName() as e where e.name like :search
     * @param conditionMap 查询条件，key为名称，value为值 如key="search" value="%aa%"
     */
    Long findCount(String countHql, Map<String, Object> conditionMap);

    /**
     * 根绝sql来查询
     * @param sql
     * @return
     */
    public List queryBySql(String sql);

}
