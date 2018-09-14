package com.mkdika.springrestapiauths.apikeyauth.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    SpringBootTest is use instead of WebMvcTest in order to apply the custom
    security config into Test environment.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApikeyauthControllerTest {

    private final String X_TOKEN = "X-Token";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void givenMessage_whenTestEcho_withoutAuth_thenReturnUnAuthorized() {
        try {
            mvc.perform(
                    get("/echo/maikel")
                            .contentType(MediaType.ALL))
                    .andExpect(status().isForbidden());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMessage_whenTestEcho_withAuth_thenReturnMessage() {
        try {
            String token = "C3AB8FF13720E8AD9047DD39466B3C8974E592C2FA383D4A3960714CAEF0C4F2";
            mvc.perform(
                    get("/echo/maikel")
                            .contentType(MediaType.ALL)
                            .header(X_TOKEN, token))
                    .andExpect(status().isOk())
                    .andExpect(content().string("maikel"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
