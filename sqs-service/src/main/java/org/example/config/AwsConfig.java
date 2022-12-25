package org.example.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AwsConfig {
    private final String sqsEndpoint;
    private final String region;
    private final String key;
    private final String secret;

    public AwsConfig(
            @Value("${aws.endpoints.sqs}") final String sqsEndpoint,
            @Value("${aws.region}") final String region,
            @Value("${aws.credentials.access-key}") final String key,
            @Value("${aws.credentials.secret-key}") final String secret
    ) {
        this.sqsEndpoint = sqsEndpoint;
        this.region = region;
        this.key = key;
        this.secret = secret;
    }

    @Bean
    public AmazonSQS amazonSqs() {
        final AmazonSQSClientBuilder amazonSqsClientBuilder = AmazonSQSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(key, secret)));
        amazonSqsClientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsEndpoint, region));

        return amazonSqsClientBuilder.build();
    }
}
