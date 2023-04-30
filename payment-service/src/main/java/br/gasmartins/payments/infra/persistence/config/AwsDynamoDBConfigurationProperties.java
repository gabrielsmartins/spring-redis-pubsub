package br.gasmartins.payments.infra.persistence.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.dynamodb")
@Getter
@Setter
@ToString
public class AwsDynamoDBConfigurationProperties {

    private String endpoint;
    private String region;
    private DynamoDbCredentialsProperties credentials;

    @ConfigurationProperties(prefix = "aws.dynamodb.credentials")
    @Getter
    @Setter
    @ToString
    public static class DynamoDbCredentialsProperties {

        private String accessKey;
        private String secretKey;
    }

}
