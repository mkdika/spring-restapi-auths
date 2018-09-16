package com.mkdika.springrestapiauths.jwtauth.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkdika.springrestapiauths.jwtauth.config.CustomAuthProvider;
import com.mkdika.springrestapiauths.jwtauth.config.dto.BasicUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtAuthControllerTest {

    private final String AUTHORIZATION = "Authorization";
    private final String username = "foo";
    private final String password = "bar";

    private ObjectMapper mapper = new ObjectMapper();
    private MockMvc mvc;
    private UsernamePasswordAuthenticationToken authc;
    private UserDetails user;
    private List<GrantedAuthority> granAuth;
    private BasicUser userReq;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CustomAuthProvider authProvider;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        userReq = new BasicUser(username, password);
        authc = new UsernamePasswordAuthenticationToken(
                username,
                password,
                new ArrayList<>());
        user = new User(username, "", true, true,
                true, true, new ArrayList<>());
        granAuth = new ArrayList<>();
        granAuth.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
    }

    @Test
    public void givenCorrectAuthData_whenTestLogin_thenReturnNoContent() {
        try {
            given(authProvider.authenticate(authc))
                    .willReturn(new UsernamePasswordAuthenticationToken(user, "", granAuth));

            // try to login (auth) and check whether the token is present
            mvc.perform(post("/login")
                    .content(json(userReq))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isNoContent())
                    .andExpect(header().string("Authorization", containsString("Bearer")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMessage_whenTestEcho_withoutAuth_thenReturnUnAuthorized() {
        try {
            // try to access Web API without auth token
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
            given(authProvider.authenticate(authc))
                    .willReturn(new UsernamePasswordAuthenticationToken(user, "", granAuth));

            // get auth token
            String authToken = mvc.perform(
                    post("/login")
                            .content(json(userReq))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getHeader("Authorization");

            // perform Web API access with token
            mvc.perform(
                    get("/echo/maikelxyz")
                            .contentType(MediaType.ALL)
                            .header(AUTHORIZATION, authToken))
                    .andExpect(status().isOk())
                    .andExpect(content().string("maikelxyz"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String json(Object o) throws IOException {
        return mapper.writeValueAsString(o);
    }
}
