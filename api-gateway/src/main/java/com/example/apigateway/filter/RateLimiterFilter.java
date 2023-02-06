package com.example.apigateway.filter;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
public class RateLimiterFilter extends GenericFilterBean {

    private final StatefulRedisConnection<String, Long> connector;
    private final Long maxRequests;

    public RateLimiterFilter(
        final StatefulRedisConnection<String, Long> connector,
        @Value("${rate-limiter.max-requests}") final Long maxRequests
    ) {
        this.connector = connector;
        this.maxRequests = maxRequests;
    }

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain
    ) throws IOException, ServletException {
        final RedisCommands<String, Long> command = connector.sync();
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final Long requestsCount = command.incr(request.getLocalAddr());
        command.expire(request.getLocalAddr(), Duration.of(1, ChronoUnit.MINUTES));
        if (requestsCount > maxRequests) {
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(429);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
