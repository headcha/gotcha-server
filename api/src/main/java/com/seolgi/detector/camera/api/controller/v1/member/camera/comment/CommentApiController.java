package com.seolgi.detector.camera.api.controller.v1.member.camera.comment;

import com.seolgi.detector.camera.api.common.auth.Role;
import com.seolgi.detector.camera.api.controller.v1.member.camera.AbstractCameraApiController;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.comment.CameraCommentService;
import com.seolgi.detector.domain.member.Member;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Secured(Role.USER)
@Api(tags = "MemberCameraCommentApi" , description = "MemberCameraCommentApi")
@RestController
@RequestMapping("/api/v1/members/cameras/{cameraId}/comments")
public class CommentApiController extends AbstractCameraApiController {

    @Autowired
    private CameraCommentService cameraCommentService;


    @PostMapping
    public ResponseEntity<CommentDto> add(@PathVariable long cameraId , @RequestBody CommentForm form , Member member) {
        Camera camera = getCamera(cameraId);
        CameraComment save = cameraCommentService.save(form.convert(camera, member));

        return ResponseEntity.ok(new CommentDto(save));
    }
}
