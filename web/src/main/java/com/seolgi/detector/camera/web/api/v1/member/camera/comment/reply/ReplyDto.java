package com.seolgi.detector.camera.web.api.v1.member.camera.comment.reply;

import com.seolgi.detector.domain.camera.comment.reply.CameraCommentReply;
import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReplyDto {
    private long id;
    private long commentId;
    private String messages;
    private Date createdAt;
    private MemberDto writer;

    public ReplyDto(CameraCommentReply reply) {
        this.id = reply.getId();
        this.commentId = reply.getComment().getId();
        this.messages = reply.getMessage();
        this.createdAt = reply.getCreatedAt();
        this.writer = new MemberDto(reply.getMember());
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
