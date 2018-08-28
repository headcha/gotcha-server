package com.seolgi.detector.domain.camera.comment.reply;

import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "camera_comment_reply")
@Getter
@NoArgsConstructor
public class CameraCommentReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean removed = false;

    @Column(length = 1000 , nullable = false)
    private String message;

    @ManyToOne(optional = false)
    @JoinColumn(name = "commentId" , nullable = false)
    private CameraComment comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "memberId" , nullable = false)
    private Member member;

    @Builder
    public CameraCommentReply(String message, CameraComment comment ,  Member member) {
        this.comment = comment;
        this.message = message;
        this.member = member;
        this.removed = false;
    }

    public void remove() {
        this.removed = true;
    }
}
