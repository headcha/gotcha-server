package com.seolgi.detector.camera.web.api.v1.member.camera;

import com.seolgi.detector.domain.camera.Camera;
import lombok.Getter;

import java.util.Date;

@Getter
public class CameraDto {
    private long id;
    private String imageUrl;
    private String message;
    private double longitude;
    private double latitude;
    private Date createdAt;
    private String memberLoginId;


    public CameraDto(Camera camera) {
        this.id = camera.getId();
        this.imageUrl = camera.getImage().getUrl();
        this.message = camera.getMessage();
        this.longitude = camera.getLocation().getLongitude();
        this.latitude = camera.getLocation().getLatitude();
        this.createdAt = camera.getCreatedAt();
        this.memberLoginId = camera.getMember().getLoginId();
    }
}
