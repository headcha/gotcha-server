package com.seolgi.detector.domain.utils;

public class SimpleMath {

    public static double rate(double current , double standard) {
        double shareRate = current / standard * 100;

        if (shareRate > 100)
            shareRate = 100;

        return shareRate;
    }
}
