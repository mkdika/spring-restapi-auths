package com.mkdika.springrestapiauths.hmacauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HmacauthApplication.class)
public class HmacauthApplicationTests {

	@Test
	public void contextLoads() {
		HmacauthApplication.main(new String[]{});
	}
}
