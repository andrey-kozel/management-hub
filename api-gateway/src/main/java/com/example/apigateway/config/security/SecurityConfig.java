package com.example.apigateway.config.security;

import com.example.apigateway.config.security.filter.JwtTokenFilter;
import com.example.apigateway.config.security.handler.SuccessAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final SuccessAuthHandler successHandler;
  private final JwtTokenFilter jwtTokenFilter;

  public static final String XSRF_COOKIE_NAME = "MH-XSRF";
  public static final String XSRF_HEADER_NAME = "MH-X-XSRF";
  public static final String COOKIE_DOMAIN = "localhost";

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .cors().and()
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
      .csrf().csrfTokenRepository(csrfTokenRepository())
      .and()
      .addFilterBefore(jwtTokenFilter, OAuth2LoginAuthenticationFilter.class)
      .build();
  }

  private CookieCsrfTokenRepository csrfTokenRepository() {
    final CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
    repository.setSecure(true);
    repository.setCookieName(XSRF_COOKIE_NAME);
    repository.setHeaderName(XSRF_HEADER_NAME);
    repository.setCookieDomain(COOKIE_DOMAIN);
    return repository;
  }

}
