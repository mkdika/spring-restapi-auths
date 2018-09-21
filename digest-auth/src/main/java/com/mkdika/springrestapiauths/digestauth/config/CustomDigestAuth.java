package com.mkdika.springrestapiauths.digestauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomDigestAuth {

    @Value("${apps.security.realmname}")
    private String realmName;

    @Value("${apps.security.key}")
    private String key;

    @Value("${apps.security.nonceseconds}")
    private Integer nonceSeconds;

    @Bean
    public DigestAuthenticationEntryPoint digestAuthenticationEntry() {
        DigestAuthenticationEntryPoint digestAuthenticationEntry = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntry.setRealmName(realmName);
        digestAuthenticationEntry.setKey(key);
        digestAuthenticationEntry.setNonceValiditySeconds(nonceSeconds);
        return digestAuthenticationEntry;
    }

    @Bean
    DigestAuthenticationFilter digestFilter(DigestAuthenticationEntryPoint digestAuthenticationEntryPoint,
                                            UserDetailsService userDetailsService) {
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint);
        filter.setUserDetailsService(userDetailsService);
        return filter;
    }
}
