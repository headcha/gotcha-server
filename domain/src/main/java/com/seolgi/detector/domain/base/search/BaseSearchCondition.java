package com.seolgi.detector.domain.base.search;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;

@Setter
@Getter
public abstract class BaseSearchCondition {
    private int memberId;
    private Date startAt = DateUtils.addYears(new Date(), -10);
    private Date endAt = new Date();
    protected int pageNo;
    protected int contentsSize = 1000;
    protected Sort sort;

    public Pageable getPageable() {
        return PageableFactory
                .create(pageNo, contentsSize, sort);
    }
}
