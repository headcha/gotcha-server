package com.seolgi.detector.domain.base.search;

import com.querydsl.core.types.dsl.BooleanExpression;

@FunctionalInterface
public interface LazyBooleanExpression
{
    BooleanExpression get();
}
