package com.mkdika.springrestapiauths.apikeyauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApikeyauthApplication.class)
public class ApikeyauthApplicationTests {

    @Test
    public void contextLoads() {
        ApikeyauthApplication.main(new String[]{});
    }

}
