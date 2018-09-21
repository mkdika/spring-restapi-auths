package com.mkdika.springrestapiauths.jwtauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is to provide the custom auth, with simple hardcoded auth account.
 */
@Component
public class CustomAuthProvider implements AuthenticationManager {

    @Value("${apps.user.name}")
    private String userName;

    @Value("${apps.user.password}")
    private String userPassword;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication.getName() == null || authentication.getCredentials() == null) {
            throw new BadCredentialsException("Credentials can not null.");
        }

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username.equals(userName) && password.equals(userPassword)) {
            UserDetails user = new User(username, "", true, true,
                     true, true, new ArrayList<>());
            List<GrantedAuthority> granAuth = new ArrayList<>();
            granAuth.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(user, "", granAuth);
        }else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }
}
