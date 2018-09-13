package com.mkdika.springrestapiauths.basicauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BasicauthApplication.class)
public class BasicauthApplicationTests {

    @Test
    public void contextLoads() {
        BasicauthApplication.main(new String[]{});
    }
}
