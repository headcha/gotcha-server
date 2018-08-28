package com.seolgi.detector.domain.camera.vote;

import com.querydsl.jpa.JPQLQuery;
import com.seolgi.detector.domain.base.BaseQueryDslSupport;
import com.seolgi.detector.domain.camera.QCamera;
import com.seolgi.detector.domain.camera.vote.type.Candidate;
import com.seolgi.detector.domain.member.QMember;

public class CameraVoteRepositoryImpl extends BaseQueryDslSupport<CameraVote> implements CameraVoteCustomRepository {
    public CameraVoteRepositoryImpl() {
        super(CameraVote.class);
    }

    @Override
    public CameraVote findOne(long cameraId, long memberId, Candidate candidate) {
        QCameraVote cameraVote = QCameraVote.cameraVote;
        QCamera camera = QCamera.camera;
        QMember member = QMember.member;

        JPQLQuery<CameraVote> query = from(cameraVote).innerJoin(cameraVote.camera, camera).innerJoin(camera.member, member);

        query.where(camera.id.eq(cameraId) , cameraVote.candidate.eq(candidate) , member.id.eq(memberId));

        return query.fetchOne();
    }
}
