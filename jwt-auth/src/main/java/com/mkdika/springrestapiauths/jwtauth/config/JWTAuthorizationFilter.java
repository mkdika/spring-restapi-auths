package com.mkdika.springrestapiauths.jwtauth.config;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is for customize the every request JWT Authorization filter.
 */
@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Value("${apps.security.secret}")
    private String secret;

    @Value("${apps.security.header-string}")
    private String headerString;

    @Value("${apps.security.token-prefix}")
    private String tokenPrefix;

    @Autowired
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String token = req.getHeader(headerString);

        // check the token is present
        if (token == null || !token.startsWith(tokenPrefix)) {
            chain.doFilter(req, res);
            return;
        }

        String user = null;
        try {
            // check the token is valid
            user = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token.replace(tokenPrefix, ""))
                    .getBody()
                    .getSubject();
        } catch (io.jsonwebtoken.SignatureException ex) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        } catch (io.jsonwebtoken.ExpiredJwtException ex) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is expired.");
        }

        if (user != null) {
            UsernamePasswordAuthenticationToken authentication
                    = new UsernamePasswordAuthenticationToken(user,
                    "",
                    null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not valid or Unauthorized.");
        }
    }
}
