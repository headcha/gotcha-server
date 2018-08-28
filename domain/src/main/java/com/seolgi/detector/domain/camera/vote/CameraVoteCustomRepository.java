package com.seolgi.detector.domain.camera.vote;

import com.seolgi.detector.domain.camera.vote.type.Candidate;

import java.util.Optional;

public interface CameraVoteCustomRepository  {
    CameraVote findOne(long cameraId, long memberId, Candidate candidate);
}
