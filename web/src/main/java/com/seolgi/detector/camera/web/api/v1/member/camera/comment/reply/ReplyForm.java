package com.seolgi.detector.camera.web.api.v1.member.camera.comment.reply;

import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.comment.reply.CameraCommentReply;
import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

@Getter
public class ReplyForm {

    private String message;

    public CameraCommentReply convert(CameraComment comment , Member member) {
        return CameraCommentReply.builder()
                .comment(comment)
                .member(member)
                .message(message)
                .build();
    }
}
