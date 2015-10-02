package com.h2ord.corp.api.dao.impl;

import com.h2ord.corp.api.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by chy on 14-9-26.
 */
public abstract class AbstractDaoImpl<E, I extends Serializable> implements
        AbstractDao<E, I> {

    private Class<E> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected AbstractDaoImpl() {

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    /**
     * 通过id得到对象
     */
    public E findById(I id) {
        return (E) this.getCurrentSession().get(this.entityClass, id);
    }

    public E loadById(I id) {
        this.getCurrentSession().clear();
        return (E) this.getCurrentSession().load(this.entityClass, id);
    }


    public E saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
        return e;
    }

    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    /**
     * criterion搜索
     */
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession()
                .createCriteria(this.entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

    public List<E> findAll() {
        String hql = "from " + this.entityClass.getName() + " as e";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }


    /**
     * 分页查询(非hql)
     */
    public List<E> findAllByPage(int start, int limit) {
        String hql = "from " + this.entityClass.getName() + " as e";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(start);
        query.setMaxResults(limit);
        return query.list();

    }

    /**
     * 分页查询(hql)
     *
     * @param hql          如：from 类.class.getName() as e where e.name like :search
     * @param conditionMap 查询条件，key为名称，value为值 如key="search" value="%aa%"
     */
    public List<E> findByHql(String hql, int start, int limit, Map<String, Object> conditionMap) {
        Query query = getCurrentSession().createQuery(hql);
        if (conditionMap != null && !conditionMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
                if (entry.getValue() instanceof Collection) {
                    query.setParameterList(entry.getKey(), (Collection) entry.getValue());

                } else if (entry.getValue().getClass().isArray()) {
                    query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
                } else {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }
        query.setFirstResult(start);
        query.setMaxResults(limit);
        return query.list();
    }

    public List<E> findByHql(String hql, Map<String, Object> conditionMap) {
        Query query = getCurrentSession().createQuery(hql);
        if (conditionMap != null && !conditionMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
                if (entry.getValue() instanceof Collection) {
                    query.setParameterList(entry.getKey(), (Collection) entry.getValue());

                } else if (entry.getValue().getClass().isArray()) {
                    query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
                } else {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }
        return query.list();
    }

    /**
     * 查找总数(非hql)
     */
    public Long findAllCount() {
        String countHql = "Select count (e) from " + this.entityClass.getName() + " as e";
        Query countQuery = getCurrentSession().createQuery(countHql);
        Long countResults = (Long) countQuery.uniqueResult();
        return countResults;
    }

    /**
     * 查找总数(hql)
     *
     * @param countHql     如：select count(e.id) from 类.class.getName() as e where e.name like :search
     * @param conditionMap 查询条件，key为名称，value为值 如key="search" value="%aa%"
     */
    public Long findCount(String countHql, Map<String, Object> conditionMap) {
        Query countQuery = getCurrentSession().createQuery(countHql);
        if (conditionMap != null && !conditionMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
                if (entry.getValue() instanceof Collection) {
                    countQuery.setParameterList(entry.getKey(), (Collection) entry.getValue());

                } else if (entry.getValue().getClass().isArray()) {
                    countQuery.setParameterList(entry.getKey(), (Object[]) entry.getValue());
                } else {
                    countQuery.setParameter(entry.getKey(), entry.getValue());
                }

            }
        }
        Long countResults = (Long) countQuery.uniqueResult();
        return countResults;
    }

    public void delete(I id) {
        E e = findById(id);
        getCurrentSession().delete(e);
    }

    /**
     * sql 查询
     *
     * @param sql
     * @return
     */
    public List queryBySql(String sql) {
        return getCurrentSession().createSQLQuery(sql).list();
    }
}