package com.seolgi.detector.camera.web.api.v1.member.camera.vote;

import com.seolgi.detector.camera.web.api.v1.member.camera.AbstractCameraApiController;
import com.seolgi.detector.camera.web.common.auth.Role;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.vote.CameraVoteService;
import com.seolgi.detector.domain.camera.vote.type.Candidate;
import com.seolgi.detector.domain.member.Member;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Secured(Role.USER)
@Api(tags = "MemberCameraVoteApi" , description = "MemberCameraVoteApi")
@RestController
@RequestMapping("/api/v1/members/cameras/{cameraId}/votes")
public class VoteApiController extends AbstractCameraApiController {

    @Autowired
    private CameraVoteService cameraVoteService;


    @PostMapping("/right")
    public void right(@PathVariable long cameraId ,   Member member) {
        Camera camera = getCamera(cameraId);
        cameraVoteService.vote(camera , member , Candidate.RIGHT);
    }

    @PostMapping("/right/cancel")
    public void cancelRight(@PathVariable long cameraId ,   Member member) {
        Camera camera = getCamera(cameraId);
        cameraVoteService.cancel(camera  , member ,Candidate.RIGHT);
    }

    @PostMapping("/wrong")
    public void wrong(@PathVariable long cameraId ,   Member member) {
        Camera camera = getCamera(cameraId);
        cameraVoteService.vote(camera ,   member, Candidate.WRONG);
    }

    @PostMapping("/wrong/cancel")
    public void cancelWrong(@PathVariable long cameraId ,   Member member) {
        Camera camera = getCamera(cameraId);
        cameraVoteService.cancel(camera ,   member, Candidate.WRONG);
    }
}
