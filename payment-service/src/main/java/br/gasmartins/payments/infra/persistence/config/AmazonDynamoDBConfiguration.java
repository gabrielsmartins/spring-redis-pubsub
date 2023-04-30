package br.gasmartins.payments.infra.persistence.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AwsDynamoDBConfigurationProperties.class)
@RequiredArgsConstructor
public class AmazonDynamoDBConfiguration {

    private final AwsDynamoDBConfigurationProperties properties;

    @Bean
    public AmazonDynamoDB dynamoDbClient() {
        var dynamoDbCredentialsProperties = this.properties.getCredentials();
        var credentials = new BasicAWSCredentials(dynamoDbCredentialsProperties.getAccessKey(),dynamoDbCredentialsProperties.getSecretKey());
        var awsCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
        return  AmazonDynamoDBClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(properties.getEndpoint(), properties.getRegion()))
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB){
        return new DynamoDBMapper(amazonDynamoDB);
    }


}