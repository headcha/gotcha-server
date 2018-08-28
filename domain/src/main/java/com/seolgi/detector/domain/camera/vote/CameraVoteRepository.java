package com.seolgi.detector.domain.camera.vote;

import com.seolgi.detector.domain.base.BaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface CameraVoteRepository extends BaseRepository<CameraVote ,Long> , CameraVoteCustomRepository {
}
