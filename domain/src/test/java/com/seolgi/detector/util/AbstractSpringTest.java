package com.seolgi.detector.util;

import com.seolgi.detector.domain.DomainApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DomainApplication.class)
@ActiveProfiles(profiles = "local")
public abstract class AbstractSpringTest {

}
