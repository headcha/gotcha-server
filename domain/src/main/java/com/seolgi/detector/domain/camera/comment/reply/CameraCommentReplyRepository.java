package com.seolgi.detector.domain.camera.comment.reply;

import com.seolgi.detector.domain.base.BaseRepository;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CameraCommentReplyRepository extends BaseRepository<CameraCommentReply , Long> {
    Page<CameraCommentReply> findByCommentId(long commentId, Pageable pageable);

    CameraCommentReply findOneByIdAndCommentId(long id, long commentId);
}
