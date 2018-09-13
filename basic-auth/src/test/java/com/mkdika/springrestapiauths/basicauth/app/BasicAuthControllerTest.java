package com.mkdika.springrestapiauths.basicauth.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BasicAuthController.class)
public class BasicAuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenMessage_whenTestEcho_withoutAuth_thenReturnUnAuthorized() {
        try {
            mvc.perform(
                    get("/echo/maikel")
                            .contentType(MediaType.ALL))
                    .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMessage_whenTestEcho_withAuth_thenReturnMessage() {
        try {
            mvc.perform(
                    get("/echo/maikel")
                            .contentType(MediaType.ALL)
                            .header("Authorization", "Basic Zm9vOmJhcg==")) // foo:bar
                    .andExpect(status().isOk())
                    .andExpect(content().string("maikel"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
