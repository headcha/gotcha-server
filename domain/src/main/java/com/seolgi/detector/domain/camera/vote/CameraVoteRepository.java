package com.seolgi.detector.domain.camera.vote;

import com.seolgi.detector.domain.base.BaseRepository;
import com.seolgi.detector.domain.camera.vote.type.Candidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface CameraVoteRepository extends BaseRepository<CameraVote ,Long> , CameraVoteCustomRepository {
    long countByCandidateAndCameraId(Candidate candidate , long camera);
    boolean existsByCandidateAndMemberId(Candidate candidate , long memberId);
}
