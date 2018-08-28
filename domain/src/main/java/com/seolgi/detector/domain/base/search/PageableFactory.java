package com.seolgi.detector.domain.base.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageableFactory {
	
	public static int DEFAULT_CONTENTS_SIZE = 100;

    public static PageRequest create(int pageNo , int contentsSize) {
        if (pageNo > 0)
            pageNo = pageNo - 1;
        
        if (contentsSize == 0) {
        	contentsSize = DEFAULT_CONTENTS_SIZE;
        }

        return new PageRequest(pageNo, contentsSize, new Sort(Sort.Direction.DESC , "id"));
    }

    public static PageRequest create(int pageNo , int contentsSize , Sort sort) {
        if (pageNo > 0)
            pageNo = pageNo - 1;
        
        if (contentsSize == 0)
        	contentsSize = DEFAULT_CONTENTS_SIZE;


        if (sort == null)
            sort = new Sort(Sort.Direction.DESC , "id");

        return new PageRequest(pageNo, contentsSize , sort);
    }

    public static PageRequest create(int pageNo) {

        if (pageNo > 0)
            pageNo = pageNo - 1;

        return new PageRequest(pageNo,  DEFAULT_CONTENTS_SIZE , new Sort(Sort.Direction.DESC , "id"));
    }

    public static PageRequest create(int pageNo, Sort sort) {

        if (pageNo > 0)
            pageNo = pageNo - 1;

        return new PageRequest(pageNo, DEFAULT_CONTENTS_SIZE , sort);
    }
}
