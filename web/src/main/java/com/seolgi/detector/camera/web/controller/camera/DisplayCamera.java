package com.seolgi.detector.camera.web.controller.camera;

import com.seolgi.detector.camera.web.api.v1.camera.comment.CommentDto;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.vote.CameraVote;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.utils.converter.PageDto;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DisplayCamera {
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
    private boolean rightVote;
    private boolean voteWrong;
    private PageDto<CommentDto> comments;

    public DisplayCamera(Camera camera, PageDto<CommentDto> comments, boolean rightVote, boolean voteWrong) {
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
        this.comments = comments;
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
