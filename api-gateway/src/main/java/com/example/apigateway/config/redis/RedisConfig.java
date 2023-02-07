package com.example.apigateway.config.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.RedisCodec;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(final RedisProperties properties) {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisClient redisClusterClient(final LettuceConnectionFactory factory) {
        return (RedisClient) factory.getNativeClient();
    }

    @Bean
    public StatefulRedisConnection<String, Long> stringLongConnector(
            final RedisClient redisClient
    ) {
        final StringLongRedisCodec codec = new StringLongRedisCodec();
        return redisClient.connect(codec);
    }

    public static class StringLongRedisCodec implements RedisCodec<String, Long> {

        @Override
        public String decodeKey(final ByteBuffer bytes) {
            return StandardCharsets.UTF_8.decode(bytes).toString();
        }

        @Override
        public Long decodeValue(final ByteBuffer bytes) {
            final CharBuffer charSequence = StandardCharsets.UTF_8.decode(bytes);
            return Long.parseLong(charSequence, 0, charSequence.length(), 10);
        }

        @Override
        public ByteBuffer encodeKey(final String key) {
            return StandardCharsets.UTF_8.encode(key);
        }

        @Override
        public ByteBuffer encodeValue(final Long value) {
            return ByteBuffer.wrap(Long.toString(value).getBytes());
        }
    }
}
