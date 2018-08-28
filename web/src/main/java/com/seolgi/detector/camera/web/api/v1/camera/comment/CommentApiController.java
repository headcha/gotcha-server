package com.seolgi.detector.camera.web.api.v1.camera.comment;

import com.seolgi.detector.domain.base.search.PageableFactory;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.comment.CameraCommentService;
import com.seolgi.detector.domain.utils.converter.PageConverter;
import com.seolgi.detector.domain.utils.converter.PageDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "CameraCommentApi" , description = "CameraCommentApi")
@RestController
@RequestMapping("/api/v1/cameras/{cameraId}/comments")
public class CommentApiController  {

    @Autowired
    private CameraCommentService cameraCommentService;


    @GetMapping
    public ResponseEntity<PageDto<CommentDto>> get(@PathVariable long cameraId , @RequestParam int page , @RequestParam int contentSize) {
        PageRequest pageRequest = PageableFactory.create(page, contentSize);
        Page<CameraComment> cameraCommentPage = cameraCommentService.get(cameraId , pageRequest);

        return ResponseEntity.ok(PageConverter.convert(cameraCommentPage , CommentDto.class));
    }
}
