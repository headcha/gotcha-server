package com.seolgi.detector.domain.camera;

import lombok.Getter;
import lombok.Setter;


@Setter
public class CameraSearchCondition {
    @Getter
    private double longitude;
    @Getter
    private double latitude;

    private int boundary;

    public double getDistanceKm() {
        return boundary * 0.001;
    }
}
