package com.seolgi.detector.domain.utils.image;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UploadImage {
    private String format;
    private String saveName;

    public String getName() {
        return saveName + "." + format;
    }
}
