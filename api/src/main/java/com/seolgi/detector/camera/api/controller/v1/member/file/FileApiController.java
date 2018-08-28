package com.seolgi.detector.camera.api.controller.v1.member.file;

import com.seolgi.detector.camera.api.common.auth.Role;
import com.seolgi.detector.domain.utils.image.ImageUploadUtil;
import com.seolgi.detector.domain.utils.image.UploadImage;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(tags = "MemberFileApi" , description = "File api")
@RestController
@RequestMapping("/api/v1/members/files/")
@Secured(Role.USER)
public class FileApiController {


    @PostMapping(value = "image")
    public ResponseEntity<UploadFile> uploadImage(@RequestParam MultipartFile image) throws IOException {
        File file = new File("/tmp/" +image.getOriginalFilename());
        image.transferTo(file);
        UploadImage uploadImage = ImageUploadUtil.upload(file);
        file.delete();
        return ResponseEntity.ok(UploadFile.builder().path(uploadImage.getName()).build());
    }
}
