package com.seolgi.detector.domain.utils.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageUploadUtil {

    public static UploadImage upload(File file) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "headcha",
                "api_key", "249477793226939",
                "api_secret", "_AHLL-IAjp8dAPVOo_rDcIC6fk0"));

        try {

            Map upload = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return UploadImage.builder()
                    .format(String.valueOf(upload.get("format")))
                    .saveName(String.valueOf(upload.get("public_id")))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
