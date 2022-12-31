package com.example.apigateway.config.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.apigateway.config.security.jwt.JwtProvider;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_NAME = "JWT";
    private final JwtProvider jwtProvider;

    @Override
    public void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws IOException, ServletException {

        final String token = getTokenFromRequest(request);

        if (jwtProvider.validateToken(token)) {
            final Claims claims = jwtProvider.getTokenClaims(token);

            final var oAuth2User = new DefaultOAuth2User(Collections.emptyList(), claims, "sub");
            final OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(
                    oAuth2User, Collections.emptyList(), "google");
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(final HttpServletRequest request) {
        return Optional.of(request)
                .map(HttpServletRequest::getCookies)
                .map(Arrays::asList)
                .filter(CollectionUtils::isNotEmpty)
                .stream()
                .flatMap(List::stream)
                .filter(cookie -> TOKEN_NAME.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse("");
    }
}
