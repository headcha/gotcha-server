package com.seolgi.detector.camera.api.controller.v1.member.camera.comment;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.member.Member;
import lombok.Getter;

@Getter
public class CommentForm {

    private String message;

    public CameraComment convert(Camera camera, Member member) {
        return CameraComment.builder()
                .camera(camera)
                .member(member)
                .message(message)
                .build();
    }
}
