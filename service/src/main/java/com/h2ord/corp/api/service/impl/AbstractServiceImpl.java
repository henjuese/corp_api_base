package com.h2ord.corp.api.service.impl;


import com.h2ord.corp.api.dao.AbstractDao;
import com.h2ord.corp.api.service.AbstractService;
import com.h2ord.corp.api.util.model.Page;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by chy on 14-9-28.
 */
@Transactional
public abstract class AbstractServiceImpl<E, I extends Serializable>
        implements AbstractService<E, I> {

    private AbstractDao<E, I> dao;

    public AbstractServiceImpl(AbstractDao<E, I> dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public E saveOrUpdate(E paramE) {
        return dao.saveOrUpdate(paramE);
    }

    @Override
    @Transactional(readOnly = true)
    public E get(I paramI) {
        return dao.findById(paramI);
    }

    @Override
    @Transactional(readOnly = true)
    public E load(I paramI) {
        return dao.loadById(paramI);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void delete(E paramE) {
        dao.delete(paramE);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findByCriteria(Criterion paramCriterion) {
        return dao.findByCriteria(paramCriterion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findByHql(String hql, int start, int limit, Map<String, Object> conditionMap) {
        return dao.findByHql(hql, start, limit, conditionMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findByHql(String hql, Map<String, Object> conditionMap) {
        return dao.findByHql(hql, conditionMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAllByPage(int start, int limit) {
        return dao.findAllByPage(start, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findAllCount() {
        return dao.findAllCount();
    }

    @Override
    @Transactional
    public void delete(I paramI) {
        dao.delete(paramI);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findCount(String countHql, Map<String, Object> conditionMap) {
        return dao.findCount(countHql, conditionMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Page getPage(int pageSize, int currentPage) {
        int start = (currentPage - 1) * pageSize;
        int count = findAllCount().intValue();
        int pageCount = (count - 1) / pageSize + 1;
        int next = 0;
        int previous = 0;
        if (currentPage < pageCount) {
            if (currentPage == 1) {
                previous = 0;
            } else {
                previous = currentPage - 1;
            }
            next = currentPage + 1;
        }
        return new Page(findAllByPage(start, pageSize), count, next, previous, pageSize, currentPage,pageCount);

    }

    @Override
    @Transactional(readOnly = true)
    public Page getPage(int pageSize, int currentPage, String hql, String countHql, Map<String, Object> conditionMap) {
        int start = (currentPage - 1) * pageSize;
        int count = findCount(countHql, conditionMap).intValue();
        int pageCount = (count - 1) / pageSize + 1;
        int next = 0;
        int previous = 0;
        if (currentPage < pageCount) {
            if (currentPage == 1) {
                previous = 0;
            } else {
                previous = currentPage - 1;
            }
            next = currentPage + 1;
        }
        return new Page(findByHql(hql, start, pageSize, conditionMap), count, next, previous, pageSize, currentPage,pageCount);


    }

    public AbstractDao<E, I> getDao() {
        return dao;
    }

    @Override
    public List queryBySql(String sql) {
        return dao.queryBySql(sql);
    }
}
