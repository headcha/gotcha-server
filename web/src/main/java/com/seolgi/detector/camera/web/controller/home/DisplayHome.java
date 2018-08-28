package com.seolgi.detector.camera.web.controller.home;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.vote.CameraVote;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DisplayHome {

    private List<CameraDto> cameras;

    public DisplayHome(List<Camera> cameras) {
        this.cameras = cameras.stream().map(camera -> new CameraDto(camera)).collect(Collectors.toList());
    }

    class CameraDto {
        private long id;
        private String message;
        private String writerLoginId;
        private Date createdAt;
        private String imageUrl;
        private double longitude;
        private double latitude;
        private int rightCount;
        private int wrongCount;

        public CameraDto(Camera camera) {
            this.id = camera.getId();
            this.message = camera.getMessage();
            this.writerLoginId = camera.getMember().getLoginId();
            this.createdAt = camera.getCreatedAt();
            this.imageUrl = camera.getImage().getUrl();
            this.longitude = camera.getLocation().getLongitude();
            this.latitude = camera.getLocation().getLatitude();
            this.rightCount = (int)camera.getVotes().stream().filter(CameraVote::isRight).count();
            this.wrongCount = (int)camera.getVotes().stream().filter(CameraVote::isWrong).count();
        }
    }
}
