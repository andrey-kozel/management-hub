package com.example.github_worker.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    private final String syncRepositoriesSqsEndpoint;
    private final String region;
    private final String key;
    private final String secret;

    public AwsConfig(
            @Value("${aws.endpoints.sqs.sync-repositories}")
            final String syncRepositoriesSqsEndpoint,
            @Value("${aws.region}")
            final String region,
            @Value("${aws.credentials.access-key}")
            final String key,
            @Value("${aws.credentials.secret-key}")
            final String secret) {
        this.syncRepositoriesSqsEndpoint = syncRepositoriesSqsEndpoint;
        this.region = region;
        this.key = key;
        this.secret = secret;
    }

    @Bean
    @Qualifier("sync-repositories")
    public AmazonSQS syncRepositoryAmazonSqs() {
        final AmazonSQSClientBuilder amazonSqsClientBuilder = AmazonSQSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(key, secret)));
        amazonSqsClientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(syncRepositoriesSqsEndpoint, region));

        return amazonSqsClientBuilder.build();
    }
}
