package com.seolgi.detector.domain.camera.comment;

import com.seolgi.detector.domain.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CameraCommentRepository extends BaseRepository<CameraComment , Long> {
    Page<CameraComment> findByCameraId(long cameraId , Pageable pageable);

    CameraComment findOneByCameraIdAndId(long cameraId, long id);
}
