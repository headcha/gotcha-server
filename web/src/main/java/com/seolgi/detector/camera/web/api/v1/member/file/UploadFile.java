package com.seolgi.detector.camera.web.api.v1.member.file;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UploadFile {
    private String path;
}
