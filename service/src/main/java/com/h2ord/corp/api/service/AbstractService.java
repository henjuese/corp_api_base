package com.h2ord.corp.api.service;

import com.h2ord.corp.api.util.model.Page;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by chy on 14-9-28.
 */
public abstract interface AbstractService<E, I extends Serializable> {

    E saveOrUpdate(E paramE);

    E get(I paramI);

    List<E> findAll();

    void delete(E paramE);

    List<E> findByCriteria(Criterion paramCriterion);

    List<E> findByHql(String hql, int start, int limit, Map<String, Object> conditionMap);

    List<E> findByHql(String hql, Map<String, Object> conditionMap);

    List<E> findAllByPage(int start, int limit);

    Long findAllCount();

    void delete(I paramI);

    Page getPage(int pageSize, int currentPage);

    Page getPage(int pageSize, int currentPage, String hql, String countQuery, Map<String, Object> conditionMap);

    Long findCount(String countHql, Map<String, Object> conditionMap);

    E load(I paramI);
    public List queryBySql(String sql);
}
