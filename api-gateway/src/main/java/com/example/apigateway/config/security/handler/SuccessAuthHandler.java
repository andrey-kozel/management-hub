
package com.example.apigateway.config.security.handler;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.apigateway.client.UserClient;
import com.example.apigateway.config.security.jwt.JwtProvider;
import com.example.apigateway.dto.SaveOrGetUserRequest;
import com.example.apigateway.dto.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SuccessAuthHandler extends SimpleUrlAuthenticationSuccessHandler {

  private static final String TOKEN_NAME = "JWT";
  private static final long expiration = Duration.ofHours(3).toSeconds();

  private final String redirectUrl;
  private final JwtProvider tokenProvider;
  private final UserClient userClient;

  public SuccessAuthHandler(
    @Value("${security.success-redirect-url}") final String redirectUrl,
    final JwtProvider tokenProvider,
    final UserClient userClient
  ) {
    this.redirectUrl = redirectUrl;
    this.tokenProvider = tokenProvider;
    this.userClient = userClient;
  }

  @Override
  public void onAuthenticationSuccess(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final Authentication authentication
  ) throws IOException {
    final DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
    final SaveOrGetUserRequest saveOrGetUserRequest = SaveOrGetUserRequest.builder()
      .accountId(principal.getName())
      .name(principal.getAttribute("name"))
      .provider("GITHUB")
      .build();
    final UserResponse result = userClient.saveOrGet(saveOrGetUserRequest);
    final String token = tokenProvider.generateToken(result.getId(), result.getLogin(), result.getAccountId(), 1);
    clearAuthenticationAttributes(request);
    final Cookie cookie = new Cookie(TOKEN_NAME, token);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge((int) expiration);
    response.addCookie(cookie);
    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
  }
}
