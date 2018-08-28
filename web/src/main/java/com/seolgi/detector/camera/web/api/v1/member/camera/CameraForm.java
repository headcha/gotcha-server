package com.seolgi.detector.camera.web.api.v1.member.camera;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

import java.util.List;

@Getter
public class CameraForm {

    private String imageUrl;
    private String message;
    private double longitude;
    private double latitude;
    private List<String> tags;
    public Camera convert(Member member) {
        return Camera.builder()
                .imageUrl(imageUrl)
                .message(message)
                .longitude(longitude)
                .latitude(latitude)
                .member(member)
                .build();
    }
}
