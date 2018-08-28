package com.seolgi.detector.camera.web.api.v1.member.camera;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.CameraService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCameraApiController {

    @Autowired
    private CameraService cameraService;

    protected Camera getCamera(long cameraId) {
        return cameraService.findOne(cameraId);
    }
}
