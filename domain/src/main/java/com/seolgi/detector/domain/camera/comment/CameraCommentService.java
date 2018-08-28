package com.seolgi.detector.domain.camera.comment;

import com.seolgi.detector.domain.base.BaseService;
import com.seolgi.detector.domain.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CameraCommentService extends BaseService<CameraComment , Long> {

    private CameraCommentRepository repository;

    @Autowired
    public CameraCommentService(CameraCommentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<CameraComment> get(long cameraId, Pageable pageable) {
        return repository.findByCameraId(cameraId , pageable);
    }

    public void remove(long cameraId, long id, long memberId) {
        CameraComment one = repository.findOneByCameraIdAndId(cameraId , id);

        if (one.getMember().getId() != memberId)
            throw new ApplicationException("다른 사람의 글은 삭제 할 수 없습니다.");

        one.remove();
    }
}
