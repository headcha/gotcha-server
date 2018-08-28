package com.seolgi.detector.camera.web.api.v1.camera;

import com.seolgi.detector.domain.camera.CameraSearchCondition;
import com.seolgi.detector.domain.utils.ModelMapperUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CameraSearchForm {
    private double longitude;
    private double latitude;
    private int boundary;

    public CameraSearchCondition createCondition() {
        return ModelMapperUtils.map(this , CameraSearchCondition.class);
    }
}
