package com.seolgi.detector.domain.camera.tag;

import com.seolgi.detector.domain.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CameraTagMapService extends BaseService<CameraTagMap , Long> {

    private CameraTagMapRepository cameraTagMapRepository;

    @Autowired
    public CameraTagMapService(CameraTagMapRepository repository){
        super(repository);
        cameraTagMapRepository = repository;
    }

}
