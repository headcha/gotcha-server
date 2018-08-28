package com.seolgi.detector.camera.web.api.v1.member.camera;

import com.seolgi.detector.camera.web.common.auth.Role;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.CameraService;
import com.seolgi.detector.domain.base.search.PageableFactory;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.utils.converter.PageConverter;
import com.seolgi.detector.domain.utils.converter.PageDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Secured(Role.USER)
@Api(tags = "MemberCameraApi" , description = "CameraApi")
@RestController
@RequestMapping("/api/v1/members/cameras")
public class CameraApiController {

    @Autowired
    private CameraService cameraService;

    @PostMapping
    public void save(@RequestBody CameraForm form , Member member) {
        cameraService.save(form.convert(member) , form.getTags());
    }

    @GetMapping
    public ResponseEntity<PageDto<CameraDto>> get(@RequestParam int pageNo , @RequestParam int contentsSize , Member member) {
        PageRequest pageable = PageableFactory.create(pageNo , contentsSize);
        Page<Camera> cameraPage = cameraService.findByMember(member.getId(), pageable);

        return ResponseEntity.ok(PageConverter.convert(cameraPage , CameraDto.class));
    }
}
