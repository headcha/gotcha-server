package com.seolgi.detector.domain.camera;

import com.seolgi.detector.domain.base.BaseService;
import com.seolgi.detector.domain.camera.tag.CameraTagMap;
import com.seolgi.detector.domain.camera.tag.CameraTagMapService;
import com.seolgi.detector.domain.tag.Tag;
import com.seolgi.detector.domain.tag.TagService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CameraService extends BaseService<Camera , Long> {

    private CameraRepository cameraRepository;

    private TagService tagService;

    public CameraService(CameraRepository repository , TagService tagService) {
        super(repository);
        this.cameraRepository = repository;
        this.tagService = tagService;
    }


    public Camera save(Camera camera , List<String> tags) {
        Camera save = super.save(camera);
        List<Tag> tagList = tagService.findOrCreate(tags, camera.getMember());
        camera.addTags(tagList);
        return save;
    }

    public Page<Camera> findToday(PageRequest pageable) {
        return cameraRepository.findToday(pageable);
    }

    public Page<Camera> findByMember(long memberId, PageRequest pageable) {
        return cameraRepository.findByMember(memberId , pageable);
    }

    public List<Camera> search(CameraSearchCondition condition) {
        return cameraRepository.search(condition);
    }
}
