package com.seolgi.detector.camera.web.api.v1.member.camera.comment.reply;

import com.seolgi.detector.camera.web.api.v1.member.camera.AbstractCameraApiController;
import com.seolgi.detector.camera.web.common.auth.Role;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.comment.CameraCommentService;
import com.seolgi.detector.domain.camera.comment.reply.CameraCommentReply;
import com.seolgi.detector.domain.camera.comment.reply.CameraCommentReplyService;
import com.seolgi.detector.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Secured(Role.USER)
@RestController
@RequestMapping("/api/v1/members/cameras/{cameraId}/comments/{commentId}/reply")
public class ReplyApiController {

    @Autowired
    private CameraCommentService cameraCommentService;

    @Autowired
    private CameraCommentReplyService cameraCommentReplyService;

    @PostMapping
    public ResponseEntity<ReplyDto> add(@PathVariable long cameraId , @PathVariable long commentId , @RequestBody ReplyForm form , Member member) {

        CameraComment comment = cameraCommentService.findOne(commentId);

        CameraCommentReply save = this.cameraCommentReplyService.save(form.convert(comment, member));

        return ResponseEntity.ok(new ReplyDto(save));
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long cameraId , @PathVariable long commentId , @PathVariable long id , Member member) {
        cameraCommentReplyService.remove(cameraId , commentId ,  id , member.getId());
    }
}
