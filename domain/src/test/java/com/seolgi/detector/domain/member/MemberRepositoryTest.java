package com.seolgi.detector.domain.member;

import com.seolgi.detector.util.AbstractSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRepositoryTest extends AbstractSpringTest {

    @Autowired
    private MemberRepository sut;

    @Test
    public void findOneByLoginId() {
        sut.findOneByLoginId("test");
    }

    @Test
    public void findOneByLoginIdAndName() {
    }

    @Test
    public void findOneByEmailAndName() {
    }
}