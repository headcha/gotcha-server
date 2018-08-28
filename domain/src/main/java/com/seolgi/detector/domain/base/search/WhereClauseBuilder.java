package com.seolgi.detector.domain.base.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;

import javax.annotation.Nullable;
import java.util.function.Function;

public class WhereClauseBuilder implements Predicate, Cloneable {
    private BooleanBuilder delegate;

    public WhereClauseBuilder() {
        this.delegate = new BooleanBuilder();
    }

    public WhereClauseBuilder(Predicate pPredicate) {
        this.delegate = new BooleanBuilder(pPredicate);
    }

    public WhereClauseBuilder and(Predicate right) {
        return new WhereClauseBuilder(delegate.and(right));
    }

    public WhereClauseBuilder or(Predicate right) {
        return new WhereClauseBuilder(delegate.or(right));
    }

    public WhereClauseBuilder andNot(Predicate right) {
        return new WhereClauseBuilder(delegate.andNot(right));
    }

    public WhereClauseBuilder orNot(Predicate right) {
        return new WhereClauseBuilder(delegate.orNot(right));
    }

    public <V> WhereClauseBuilder optionalAnd(@Nullable V pValue, LazyBooleanExpression pBooleanExpression) {
        return applyIfNotNull(pValue, this::and, pBooleanExpression);
    }

    public WhereClauseBuilder optionalAnd(boolean value, LazyBooleanExpression pBooleanExpression) {
        return applyIfTrue(value, this::and, pBooleanExpression);
    }

    public WhereClauseBuilder optionalOr(boolean value, LazyBooleanExpression pBooleanExpression) {
        return applyIfTrue(value, this::or, pBooleanExpression);
    }

    public WhereClauseBuilder optionalAnd(int value, LazyBooleanExpression pBooleanExpression) {
        return applyIfTrue(value > 0, this::and, pBooleanExpression);
    }

    public WhereClauseBuilder optionalOr(int value, LazyBooleanExpression pBooleanExpression) {
        return applyIfTrue(value > 0, this::or, pBooleanExpression);
    }

    public <V> WhereClauseBuilder optionalOr(@Nullable V pValue, LazyBooleanExpression pBooleanExpression) {
        return applyIfNotNull(pValue, this::or, pBooleanExpression);
    }

    public <V> WhereClauseBuilder optionalAndNot(@Nullable V pValue, LazyBooleanExpression pBooleanExpression) {
        return applyIfNotNull(pValue, this::andNot, pBooleanExpression);
    }

    public <V> WhereClauseBuilder optionalOrNot(@Nullable V pValue, LazyBooleanExpression pBooleanExpression) {
        return applyIfNotNull(pValue, this::orNot, pBooleanExpression);
    }

    private <V> WhereClauseBuilder applyIfNotNull(@Nullable V pValue, Function<Predicate, WhereClauseBuilder> pFunction, LazyBooleanExpression pBooleanExpression) {
        if (pValue != null) {
            return new WhereClauseBuilder(pFunction.apply(pBooleanExpression.get()));
        }
        return this;
    }

    private <V> WhereClauseBuilder applyIfTrue(boolean value, Function<Predicate, WhereClauseBuilder> pFunction, LazyBooleanExpression pBooleanExpression) {
        if (value) {
            return new WhereClauseBuilder(pFunction.apply(pBooleanExpression.get()));
        }
        return this;
    }

    private WhereClauseBuilder apply(Function<Predicate, WhereClauseBuilder> pFunction, LazyBooleanExpression pBooleanExpression) {
        return new WhereClauseBuilder(pFunction.apply(pBooleanExpression.get()));
    }

    private <V> boolean isNull(@Nullable V pValue) {
        if (pValue == null) return true;

        return false;
    }

    @Override
    public Predicate not() {
        return delegate.not();
    }

    @Nullable
    @Override
    public <R, C> R accept(Visitor<R, C> visitor, @Nullable C c) {
        return delegate.accept(visitor, c);
    }

    @Override
    public Class<? extends Boolean> getType() {
        return delegate.getType();
    }
}