package com.seolgi.detector.domain.camera.vote;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.vote.type.Candidate;
import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "camera_vote")
@Getter
@NoArgsConstructor
public class CameraVote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Candidate candidate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cameraId")
    private Camera camera;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public CameraVote(Candidate candidate, Camera camera, Member member) {
        this.candidate = candidate;
        this.camera = camera;
        this.member = member;
    }

    public boolean isRight() {
        return candidate.equals(Candidate.RIGHT);
    }

    public boolean isWrong() {
        return candidate.equals(Candidate.WRONG);
    }
}
