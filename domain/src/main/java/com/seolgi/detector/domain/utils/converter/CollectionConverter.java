package com.seolgi.detector.domain.utils.converter;

import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;


public class CollectionConverter<T, K> {


    public static<T,K> List<K> convert(List<T> originalList, Class<K> targetType, String methodName) {
        try {
            List<K> convertList = new ArrayList<>();
            for (T originalInstance : originalList) {
                if (isProxy(originalInstance)) {
                    originalInstance = (T) ((HibernateProxy) originalInstance).getHibernateLazyInitializer().getImplementation();
                }
                convertList.add((K) targetType.getDeclaredMethod(methodName, originalInstance.getClass()).invoke(targetType.newInstance(), originalInstance));
            }
            return convertList;

        } catch (Exception ex) {
            throw new RuntimeException("method invoke Exception", ex);
        }
    }

    private static boolean isProxy(Object obj) {
        return obj instanceof HibernateProxy;
    }

    public static<T,K> List<K> convert(List<T> originalList, Class<K> targetType) {
        try {
            List<K> convertList = new ArrayList<>();
            for (T originalInstance : originalList) {

                if (isProxy(originalInstance)) {
                    originalInstance = (T) ((HibernateProxy) originalInstance).getHibernateLazyInitializer().getImplementation();
                }

                convertList.add(targetType.getConstructor(originalInstance.getClass()).newInstance(originalInstance.getClass()));
            }
            return convertList;

        } catch (Exception ex) {
            throw new RuntimeException("method invoke Exception", ex);
        }
    }
}
