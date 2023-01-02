package com.example.apigateway.config.security;

import com.example.apigateway.config.security.filter.JwtCsrfFilter;
import com.example.apigateway.config.security.filter.JwtTokenFilter;
import com.example.apigateway.config.security.handler.SuccessAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final SuccessAuthHandler successHandler;
  private final JwtTokenFilter jwtTokenFilter;

  private final JwtCsrfFilter jwtCsrfFilter;

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
      .addFilterAt(jwtCsrfFilter, CsrfFilter.class)
      .csrf().ignoringAntMatchers("/**")
      .and()
      .addFilterBefore(jwtTokenFilter, OAuth2LoginAuthenticationFilter.class)
      .build();
  }

}
