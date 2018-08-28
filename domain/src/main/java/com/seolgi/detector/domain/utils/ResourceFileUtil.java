package com.seolgi.detector.domain.utils;

import java.io.File;
import java.io.InputStream;


public class ResourceFileUtil {
    private ResourceFileUtil(){}
    /**
     * src/main/resources/ 경로가 root 경로
     * root 는 제외하고 나머지 경로만 넣는다
     * 상대 경로로 위치를 넣어야 한다
     * @param filePath
     * @return InputStream
     */
    public static InputStream resourceFileInputStream(String filePath) {
        return ResourceFileUtil.class.getClassLoader().getResourceAsStream(filePath);
    }

    public static File resourceFile(String filePath) {
        return new File(ResourceFileUtil.class.getClassLoader().getResource(filePath).getPath());
    }
}
