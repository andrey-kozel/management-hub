package org.example.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AwsConfig {
    private final String sqsEndpoint;
    private final String region;
    private final String key;
    private final String secret;
    private final String name;


    public AwsConfig(
            @Value("${aws.endpoints.sqs}") final String sqsEndpoint,
            @Value("${aws.region}") final String region,
            @Value("${aws.credentials.access-key}") final String key,
            @Value("${aws.credentials.secret-key}") final String secret,
            @Value("repositories-sync") final String name
    ) {
        this.sqsEndpoint = sqsEndpoint;
        this.region = region;
        this.key = key;
        this.secret = secret;
        this.name = name;
    }

    @Bean
    public AmazonSQS amazonSqs() {
        final AmazonSQSClientBuilder amazonSqsClientBuilder = AmazonSQSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(key, secret)));
        amazonSqsClientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsEndpoint, region));

        return amazonSqsClientBuilder.build();
    }

    @Bean
    public CreateQueueRequest queueCreationRequest() {

        return new CreateQueueRequest(name)
                .addAttributesEntry("DelaySeconds", "60")
                .addAttributesEntry("MessageRetentionPeriod", "86400");
    }

    @Bean
    public String createQueue(AmazonSQS amazonSqs, CreateQueueRequest createQueueRequest) {
        try {
            amazonSqs.createQueue(createQueueRequest);
        } catch (AmazonSQSException sqsException) {
            if (!sqsException.getErrorCode().equals("QueueAlreadyExists")) {
                throw sqsException;
            }
        }
        return createQueueRequest.getQueueName();
    }

}