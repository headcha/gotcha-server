package com.seolgi.detector.camera.api.controller.v1.camera;

import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.vote.CameraVote;
import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CameraDto {
    private long id;
    private String imageUrl;
    private String message;
    private double longitude;
    private double latitude;
    private Date createdAt;
    private MemberDto writer;
    private int voterCount;
    private List<String> tags;
    private int commentCount;
    private int rightCount;
    private int wrongCount;
    private List<CommentDto> comments;

    public CameraDto(Camera camera) {
        this.id = camera.getId();
        this.imageUrl = camera.getImage().getUrl();
        this.message = camera.getMessage();
        this.longitude = camera.getLocation().getLongitude();
        this.latitude = camera.getLocation().getLatitude();
        this.createdAt = camera.getCreatedAt();
        this.writer = new MemberDto(camera.getMember());
        this.voterCount = camera.getVotes().size();
        this.tags = camera.getTagMaps().stream().map(cameraTagMap -> cameraTagMap.getTag().getTag()).collect(Collectors.toList());
        this.commentCount = camera.getComments().size();
        this.rightCount = (int) camera.getVotes().stream().filter(CameraVote::isRight).count();
        this.wrongCount = (int) camera.getVotes().stream().filter(CameraVote::isWrong).count();
        this.comments = camera.getComments().stream().filter(BaseEntity::isEnabled).map(cameraComment -> new CommentDto(cameraComment)).collect(Collectors.toList());
    }

    @Getter
    static class CommentDto {
        private long id;
        private String message;
        private Date createdAt;
        private MemberDto writer;

        public CommentDto(CameraComment cameraComment) {
            this.id = cameraComment.getId();
            this.message = cameraComment.getMessage();
            this.createdAt = cameraComment.getCreatedAt();
            this.writer = new MemberDto(cameraComment.getMember());
        }
    }

    @Getter
    static class MemberDto {
        private long id;
        private String loginId;
        private String name;
        public MemberDto(Member member) {
            this.id = member.getId();
            this.loginId = member.getLoginId();
            this.name = member.getName();
        }
    }

}
