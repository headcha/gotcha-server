package com.seolgi.detector.domain.utils;

import com.seolgi.detector.domain.common.context.AppContextManager;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperUtils {


    public static <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        if (source == null)
            return null;

        return getModelMapper().map(source, destinationClass);
    }

    public static <T> List<T> mapList(List<?> listSources, Class<T> destinationClass) {
        List<T> resultList = new ArrayList<>();

        if (!ArrayUtils.isEmpty(listSources)) {
            for (Object source : listSources) {
                resultList.add(map(source, destinationClass));
            }
        }

        return resultList;
    }


    public static <T> Page<T> page(Page<?> page, Class<T> destinationClass) {
        List<T> resultList = mapList(page.getContent() , destinationClass);
        return new PageImpl<>(resultList , new PageRequest(page.getNumber() , page.getSize() , page.getSort()), page.getTotalElements());

    }

    public static ModelMapper getModelMapper() {
        return AppContextManager.getBean(ModelMapper.class);
    }

}
