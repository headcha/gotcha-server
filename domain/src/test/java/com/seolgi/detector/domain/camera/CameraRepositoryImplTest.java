package com.seolgi.detector.domain.camera;

import com.seolgi.detector.domain.member.MemberRepository;
import com.seolgi.detector.util.AbstractSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CameraRepositoryImplTest extends AbstractSpringTest {

    @Autowired
    private CameraRepository sut;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void search() {

        CameraSearchCondition condition = new CameraSearchCondition();
        condition.setBoundary(300);
        condition.setLatitude(37.553451);
        condition.setLongitude(126.972389);
        List<Camera> search = sut.search(condition);
        System.out.println(search.size());
    }
}