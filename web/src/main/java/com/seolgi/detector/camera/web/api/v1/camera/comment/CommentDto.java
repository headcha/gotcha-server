package com.seolgi.detector.camera.web.api.v1.camera.comment;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.comment.reply.CameraCommentReply;
import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentDto {
    private long id;
    private boolean removed;
    private CameraDto camera;
    private String messages;
    private Date createdAt;
    private List<ReplyDto> replies;
    private MemberDto writer;

    public CommentDto(CameraComment cameraComment) {
        this.id = cameraComment.getId();
        this.removed = cameraComment.isRemoved();
        this.camera = new CameraDto(cameraComment.getCamera());
        this.messages = cameraComment.getMessage();
        this.createdAt = cameraComment.getCreatedAt();
        this.replies = cameraComment.getReplies().stream().map(reply -> new ReplyDto(reply)).collect(Collectors.toList());
        this.writer = new MemberDto(cameraComment.getMember());
    }


    @Getter
    static class CameraDto {
        private long id;
        public CameraDto(Camera camera) {
            this.id = camera.getId();
        }
    }

    @Getter
    static class ReplyDto {
        private long id;
        private String messages;
        private Date createdAt;
        private MemberDto writer;
        public ReplyDto(CameraCommentReply reply) {
            this.id = reply.getId();
            this.messages = reply.getMessage();
            this.createdAt = reply.getCreatedAt();
            this.writer = new MemberDto(reply.getMember());
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
