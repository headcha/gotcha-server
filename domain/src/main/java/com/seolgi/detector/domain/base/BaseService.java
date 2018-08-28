package com.seolgi.detector.domain.base;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public abstract class BaseService<T extends BaseEntity, ID extends Serializable> {

    public BaseService() {

    }

    protected BaseRepository<T, ID> repository;

    public BaseService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }


    public List<T> findAll(Iterable<ID> iterable) {
        return repository.findAll(iterable);
    }

    public T findOne(Predicate predicate) {
        return repository.findOne(predicate);
    }

    public List<T> findAll(Predicate predicate) {
        return toList(repository.findAll(predicate));
    }

    public List<T> findAll(Predicate predicate, Sort sort) {
        return toList(repository.findAll(predicate, sort));
    }

    public List<T> toList(Iterable<T> iterable) {
        if (iterable != null) {
            List<T> list = new ArrayList<>();
            for (T item : iterable) {
                list.add(item);
            }

            return list;
        }

        return Collections.emptyList();
    }

    public T enable(ID var1) {
        T one = getOne(var1);
        one.enable();
        return one;
    }

    @Transactional
    public T disable(ID var1) {
        T one = getOne(var1);
        one.disable();
        return one;
    }

    public List<T> findAll(Predicate predicate, OrderSpecifier... orderSpecifiers) {
        return toList(repository.findAll(predicate, orderSpecifiers));
    }

    public List<T> findAll(OrderSpecifier... orderSpecifiers) {
        return toList(repository.findAll(orderSpecifiers));
    }

    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    public long count(Predicate predicate) {
        return repository.count(predicate);
    }

    public boolean exists(Predicate predicate) {
        return repository.exists(predicate);
    }

    public void flush() {
        repository.flush();
    }

    @Transactional
    public <S extends T> S saveAndFlush(S object) {
        return repository.saveAndFlush(object);
    }

    @Transactional
    public void deleteInBatch(Iterable<T> iterable) {
        repository.deleteInBatch(iterable);
    }

    @Transactional
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    public T getOne(ID id) {
        return repository.getOne(id);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public <S extends T> S save(S var) {
        repository.save(var);
        return var;
    }

    @Transactional
    public <S extends T> Collection<S> save(Collection<S> vars) {
        vars.forEach(this::save);
        return vars;
    }

    public T findOne(ID var1) {
        return repository.findOne(var1);
    }

    public boolean exists(ID var1) {
        return repository.exists(var1);
    }

    public long count() {
        return repository.count();
    }

    @Transactional
    public void delete(ID var1) {
        repository.delete(var1);
    }

    @Transactional
    public void delete(T var1) {
        repository.delete(var1);
    }

    @Transactional
    public void delete(Iterable<? extends T> var1) {
        repository.delete(var1);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @PersistenceContext
    protected EntityManager em;


    protected String like(String field) {
        return "%" + field + "%";
    }

    public boolean isNotEmpty(String value) {
        return StringUtils.isNotEmpty(value);
    }

    public boolean isEmpty(String value) {
        return StringUtils.isEmpty(value);
    }

    public boolean isEmpty(Collection<?> list) {
        return list == null || list.size() == 0;
    }

    public boolean isNotEmpty(Collection<?> list) {
        return !isEmpty(list);
    }

    public boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return false;
        }

        if (o2 == null) {
            return false;
        }

        return o1.equals(o2);
    }

    public boolean notEquals(Object o1, Object o2) {
        return !equals(o1, o2);
    }

}
