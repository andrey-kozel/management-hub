package com.example.apigateway.config.security.jwt;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider {

  private String jwtSecret;
  private Long sessionDurationMinutes;

  public JwtProvider(
    @Value("${security.secret}") final String jwtSecret,
    @Value("${security.session-duration-minutes}") final Long sessionDurationMinutes
  ) {
    this.jwtSecret = jwtSecret;
    this.sessionDurationMinutes = sessionDurationMinutes;
  }

  public String generateToken(final Long name, final String login, final String accountId) {
    final Instant instant = LocalDateTime.now()
      .plus(Duration.ofMinutes(sessionDurationMinutes))
      .toInstant(ZoneOffset.UTC);
    final Date date = Date.from(instant);
    return Jwts.builder()
      .setSubject(name.toString())
      .claim("login", login)
      .claim("accountId", accountId)
      .setExpiration(date)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
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
}
