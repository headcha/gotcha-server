package com.seolgi.detector.domain.camera.comment.reply;

import com.seolgi.detector.domain.base.BaseService;
import com.seolgi.detector.domain.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CameraCommentReplyService extends BaseService<CameraCommentReply , Long> {

    private CameraCommentReplyRepository repository;

    @Autowired
    public CameraCommentReplyService(CameraCommentReplyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<CameraCommentReply> get(long commentId, Pageable pageable) {
        return repository.findByCommentId(commentId , pageable);
    }

    public void remove(long cameraId, long commentId, long id, long memberId) {
        CameraCommentReply reply = repository.findOneByIdAndCommentId(id , commentId);

        if (reply.getMember().getId() != memberId)
            throw new ApplicationException("다른 사람의 글은 삭제 할 수 없습니다.");

        reply.remove();
    }
}
