package com.seolgi.detector.domain.camera.comment;

import com.seolgi.detector.domain.base.search.PageableFactory;
import com.seolgi.detector.util.AbstractSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CameraCommentRepositoryTest extends AbstractSpringTest {

    @Autowired
    private CameraCommentRepository sut;

    @Test
    public void findByCameraId() {

        sut.findByCameraId(1 , PageableFactory.create(1));
    }
}