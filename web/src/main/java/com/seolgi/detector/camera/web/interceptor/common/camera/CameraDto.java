package com.seolgi.detector.camera.web.interceptor.common.camera;

import com.seolgi.detector.domain.camera.Camera;
import lombok.Getter;

import java.util.Date;

@Getter
public class CameraDto {
    private long id;
    private String imageUrl;
    private String messages;
    private double longitude;
    private double latitude;
    private Date createdAt;


    public CameraDto(Camera camera) {
        this.id = camera.getId();
        this.imageUrl = camera.getImage().getUrl();
        this.messages = camera.getMessage();
        this.longitude = camera.getLocation().getLongitude();
        this.latitude = camera.getLocation().getLatitude();
        this.createdAt = camera.getCreatedAt();


    }


}
