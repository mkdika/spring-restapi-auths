package com.mkdika.springrestapiauths.jwtauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JwtauthApplication.class)
public class JwtauthApplicationTests {

	@Test
	public void contextLoads() {
		JwtauthApplication.main(new String[]{});
	}

}
