package com.seolgi.detector.domain.camera;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CameraCustomRepository {
    Page<Camera> findByMember(long memberId, PageRequest pageable);

    List<Camera> search(CameraSearchCondition condition);

    Page<Camera> findToday(PageRequest pageable);
}
