package com.seolgi.detector.camera.api.controller.v1.camera.comment;

import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

import java.util.Date;

@Getter
public class CommentDto {
    private long id;
    private String message;
    private Date createdAt;
    private MemberDto commenter;

    public CommentDto(CameraComment cameraComment) {
        this.id = cameraComment.getId();
        this.message = cameraComment.getMessage();
        this.createdAt = cameraComment.getCreatedAt();
        this.commenter = new MemberDto(cameraComment.getMember());
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
