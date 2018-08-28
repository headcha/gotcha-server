package com.seolgi.detector.domain.utils;

import java.util.ArrayList;
import java.util.Collection;

public class ArrayUtils {

    public static boolean isEmpty(Collection<?> list) {
        return !(list != null && list.size() > 0);
    }

    public static boolean isNotEmpty(Collection<?> list) {
        return (list != null && list.size() > 0);
    }


    public static Collection subtract(Collection firstCollection, Collection secondCollection) {
        Collection collection = new ArrayList<>();

        for (Object object : firstCollection) {
            if (!secondCollection.contains(object)) {
                collection.add(object);
            }
        }

        return collection;
    }
}