package com.seolgi.detector.domain.camera.vote;

import com.seolgi.detector.domain.base.BaseService;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.vote.type.Candidate;
import com.seolgi.detector.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CameraVoteService extends BaseService<CameraVote, Long> {

    private CameraVoteRepository cameraVoteRepository;


    @Autowired
    public CameraVoteService(CameraVoteRepository repository) {
        super(repository);
        cameraVoteRepository = repository;
    }

    public void vote(Camera camera, Member member, Candidate candidate) {
        Optional<CameraVote> one = cameraVoteRepository.findOne(camera.getId(), member.getId(), candidate);

        if (one.isPresent() == false)
            super.save(CameraVote.builder().camera(camera).candidate(candidate).member(member).build());

    }

    public void cancel(Camera camera, Member member, Candidate candidate) {
        Optional<CameraVote> one = cameraVoteRepository.findOne(camera.getId(), member.getId(), candidate);
        one.ifPresent(CameraVote::disable);
    }
}
