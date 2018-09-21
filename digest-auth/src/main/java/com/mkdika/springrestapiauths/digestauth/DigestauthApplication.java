package com.mkdika.springrestapiauths.digestauth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import javax.servlet.Filter;

@SpringBootApplication
public class DigestauthApplication {

    @Value("${apps.security.realmname}")
    private String realmName;

    @Value("${apps.security.key}")
    private String key;

    @Value("${apps.security.nonceseconds}")
    private Integer nonceSeconds;

    public static void main(String[] args) {
        SpringApplication.run(DigestauthApplication.class, args);
    }

    @Bean
    DigestAuthenticationFilter digestFilter(DigestAuthenticationEntryPoint digestAuthenticationEntryPoint,
                                            UserDetailsService userDetailsService,
                                            UserCache digestUserCache) {
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint);
        filter.setUserDetailsService(userDetailsService);
        filter.setUserCache(digestUserCache);
        return filter;
    }

    @Bean
    UserCache digestUserCache() throws Exception {
        return new SpringCacheBasedUserCache(new ConcurrentMapCache("digestUserCache"));
    }

    @Bean
    DigestAuthenticationEntryPoint digestAuthenticationEntry() {
        DigestAuthenticationEntryPoint digestAuthenticationEntry = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntry.setRealmName(realmName);
        digestAuthenticationEntry.setKey(key);
        digestAuthenticationEntry.setNonceValiditySeconds(nonceSeconds);
        return digestAuthenticationEntry;
    }

    @Bean
    FilterRegistrationBean customSecurityHeaderFilterRegistrationBean(
            @Qualifier("CustomSecurityHeaderFilter") Filter CustomSecurityHeaderFilter) {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(CustomSecurityHeaderFilter);
        filterRegistration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistration;
    }
}
