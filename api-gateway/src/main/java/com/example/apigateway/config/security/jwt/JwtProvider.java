package com.example.apigateway.config.security.jwt;

import java.time.*;
import java.util.*;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;

@Slf4j
@Component
public class JwtProvider implements CsrfTokenRepository {

  private String jwtSecret;
  private Long sessionDurationMinutes;

  public static final String CSRF_TOKEN_NAME = "CSRF_TOKEN";

  public JwtProvider(
    @Value("${security.secret}") final String jwtSecret,
    @Value("${security.session-duration-minutes}") final Long sessionDurationMinutes
  ) {
    this.jwtSecret = jwtSecret;
    this.sessionDurationMinutes = sessionDurationMinutes;
  }

  public String generateToken(final Long id, final String login, final String accountId) {
    final Instant instant = LocalDateTime.now()
      .plus(Duration.ofMinutes(sessionDurationMinutes))
      .toInstant(ZoneOffset.UTC);
    final Date date = Date.from(instant);
    return Jwts.builder()
      .setSubject(id.toString())
      .claim("login", login)
      .claim("accountId", accountId)
      .setExpiration(date)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      log.info("Validation token");
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      log.info("Token is valid");
      return true;
    } catch (ExpiredJwtException expEx) {
      log.info("Token expired");
    } catch (UnsupportedJwtException unsEx) {
      log.info("Unsupported jwt");
    } catch (MalformedJwtException mjEx) {
      log.info("Malformed jwt");
    } catch (SignatureException sEx) {
      log.info("Invalid signature");
    } catch (Exception e) {
      log.info("invalid token");
    }
    return false;
  }

  public Claims getTokenClaims(final String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
  }

  @Override
  public CsrfToken generateToken(HttpServletRequest request) {
    String id = UUID.randomUUID().toString().replace("-", "");
    Date now = new Date();
    Date exp = Date.from(LocalDateTime.now().plusMinutes(30)
            .atZone(ZoneId.systemDefault()).toInstant());

    String token = "";
    try {
      token = Jwts.builder()
              .setId(id)
              .setIssuedAt(now)
              .setNotBefore(now)
              .setExpiration(exp)
              .signWith(SignatureAlgorithm.HS256, jwtSecret)
              .compact();
    } catch (JwtException e) {
      log.error("An error occurred when generating CSRF token: " + e);
    }
    return new DefaultCsrfToken("x-csrf-token", "_csrf", token);
  }

  @Override
  public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
    if (Objects.nonNull(csrfToken)) {
      if (!response.getHeaderNames().contains(ACCESS_CONTROL_EXPOSE_HEADERS))
        response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, csrfToken.getHeaderName());

      if (response.getHeaderNames().contains(csrfToken.getHeaderName()))
        response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
      else
        response.addHeader(csrfToken.getHeaderName(), csrfToken.getToken());
    }
  }

  @Override
  public CsrfToken loadToken(HttpServletRequest request) {
    String token = Optional.of(request)
            .map(HttpServletRequest::getCookies)
            .map(Arrays::asList)
            .filter(CollectionUtils::isNotEmpty)
            .stream()
            .flatMap(List::stream)
            .filter(cookie -> CSRF_TOKEN_NAME.equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst()
            .orElse("");
    return new DefaultCsrfToken("x-csrf-token", "_csrf", token);
  }

  public void clearCsrfToken(HttpServletResponse response) {
    if (response.getHeaderNames().contains("x-csrf-token"))
      response.setHeader("x-csrf-token", "");
  }
}
