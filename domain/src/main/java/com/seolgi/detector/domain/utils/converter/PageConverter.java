package com.seolgi.detector.domain.utils.converter;

import org.springframework.data.domain.Page;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class PageConverter {

    public static<T , S> PageDto<S> convert(Page<T> page , Class<S> destinationClass) {
        try {

            List<T> content = page.getContent();
            List<S> resultList = new ArrayList<>();

            for (T originalInstance : content) {
                Constructor<S> constructor = destinationClass.getConstructor(originalInstance.getClass());
                resultList.add(constructor.newInstance(originalInstance));
            }

            return new PageDto(page , resultList);
        } catch (Exception ex) {
            throw  new RuntimeException(ex);
        }
    }
}
