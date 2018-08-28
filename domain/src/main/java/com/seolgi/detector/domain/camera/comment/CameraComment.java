package com.seolgi.detector.domain.camera.comment;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.camera.comment.reply.CameraCommentReply;
import com.seolgi.detector.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "camera_comment")
@Getter
@NoArgsConstructor
public class CameraComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean removed = false;

    @Column(length = 1000 , nullable = false)
    private String message;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cameraId" , nullable = false)
    private Camera camera;

    @OneToMany(mappedBy = "comment")
    private List<CameraCommentReply> replies = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "memberId" , nullable = false)
    private Member member;

    @Builder
    public CameraComment(String message, Camera camera, Member member) {
        this.message = message;
        this.camera = camera;
        this.member = member;
        this.removed = false;
        this.replies = new ArrayList<>();
    }


    public void remove() {
        this.removed = true;
    }
}
