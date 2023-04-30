package br.gasmartins.orders.infra.messaging.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "topic")
@Getter
@Setter
@ToString
public class RedisTopicProperties {

    private String orderTopic;
    private String paymentTopic;

}
