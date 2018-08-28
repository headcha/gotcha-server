package com.seolgi.detector.domain.base;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public abstract class BaseQueryDslSupport<T> extends QueryDslRepositorySupport {
    public BaseQueryDslSupport(Class<?> domainClass) {
        super(domainClass);
    }


    protected Page<T> sortAndPagination(JPQLQuery<T> query , Pageable pageable) {
        List<T> list = getQuerydsl().applySorting(pageable.getSort(), query).limit(pageable.getPageSize()).offset(pageable.getOffset()).fetch();
        return new PageImpl<>(list, pageable, query.fetchCount());
    }


}
