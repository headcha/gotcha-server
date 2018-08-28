package com.seolgi.detector.domain.utils.converter;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageDto<T> {
    private int totalCount;
    private int currentPageNo;
    private int contentsSize;
    private boolean first;
    private boolean last;
    private List<T> contents;

    public PageDto(Page page, List<T> list) {
        this.totalCount = (int) page.getTotalElements();
        this.currentPageNo = page.getNumber();
        this.first = page.isFirst();
        this.last = page.isLast();
        this.contentsSize = page.getNumberOfElements();
        this.contents = list;
    }

}
