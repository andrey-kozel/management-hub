package com.example.apigateway.config.security;

import com.example.apigateway.config.security.filter.JwtTokenFilter;
import com.example.apigateway.config.security.handler.SuccessAuthHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    private final SuccessAuthHandler successHandler;
    private final JwtTokenFilter jwtTokenFilter;
    public final String xsrfCookieName;
    public final String xsrfHeaderName;
    public final String cookieDomain;

    public SecurityConfig(
        final SuccessAuthHandler successHandler,
        final JwtTokenFilter jwtTokenFilter,
        @Value("${csrf.xsrf_cookie_name}") final String xsrfCookieName,
        @Value("${csrf.xsrf_header_name}") final String xsrfHeaderName,
        @Value("${csrf.cookie_domain}") final String cookieDomain
    ) {
        this.successHandler = successHandler;
        this.jwtTokenFilter = jwtTokenFilter;
        this.xsrfCookieName = xsrfCookieName;
        this.xsrfHeaderName = xsrfHeaderName;
        this.cookieDomain = cookieDomain;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().csrfTokenRepository(csrfTokenRepository())
                .and()
                .authorizeRequests(a -> a
                        .antMatchers("/", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2Login()
                .successHandler(successHandler)
                .and()
                .addFilterBefore(jwtTokenFilter, OAuth2LoginAuthenticationFilter.class)
                .build();
    }

    private CookieCsrfTokenRepository csrfTokenRepository() {
        final CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setSecure(true);
        repository.setCookieName(xsrfCookieName);
        repository.setHeaderName(xsrfHeaderName);
        repository.setCookieDomain(cookieDomain);
        return repository;
    }
}
