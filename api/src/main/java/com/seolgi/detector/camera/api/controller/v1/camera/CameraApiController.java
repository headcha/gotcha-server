package com.seolgi.detector.camera.api.controller.v1.camera;

import com.seolgi.detector.domain.base.search.PageableFactory;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.CameraService;
import com.seolgi.detector.domain.utils.converter.PageConverter;
import com.seolgi.detector.domain.utils.converter.PageDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "CameraApi" , description = "CameraApi")
@RestController
@RequestMapping("/api/v1/cameras")
public class CameraApiController {

    @Autowired
    private CameraService cameraService;

    @GetMapping
    public ResponseEntity<PageDto<CameraDto>> get(@RequestParam int pageNo , @RequestParam int contentsSize) {
        PageRequest pageable = PageableFactory.create(pageNo , contentsSize);
        Page<Camera> page = cameraService.findAll(pageable);
        return ResponseEntity.ok(PageConverter.convert(page ,  CameraDto.class));
    }

    @GetMapping("/{cameraId}")
    public ResponseEntity<CameraDto> getOne(@PathVariable long cameraId) {

        Camera one = cameraService.findOne(cameraId);
        return ResponseEntity.ok(new CameraDto(one));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CameraDto>> search(@ModelAttribute CameraSearchForm form) {
        List<Camera> cameras = cameraService.search(form.createCondition());


        List<CameraDto> convert = cameras.stream().map(camera -> new CameraDto(camera)).collect(Collectors.toList());
        return ResponseEntity.ok(convert);
    }

}
