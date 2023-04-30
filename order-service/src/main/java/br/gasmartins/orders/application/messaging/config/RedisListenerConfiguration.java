package br.gasmartins.orders.application.messaging.config;

import br.gasmartins.orders.application.messaging.subscriber.PaymentMessageSubscriber;
import br.gasmartins.orders.application.messaging.subscriber.dto.PaymentMessageDto;
import br.gasmartins.orders.infra.messaging.config.RedisTopicProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisTopicProperties.class)
public class RedisListenerConfiguration {

    private final RedisTopicProperties properties;

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
        return objectMapper;
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(MessageListenerAdapter adapter, RedisConnectionFactory connectionFactory) {
        var container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(adapter, new PatternTopic(properties.getPaymentTopic()));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListener(PaymentMessageSubscriber subscriber, ObjectMapper objectMapper) {
        var adapter = new MessageListenerAdapter(subscriber);
        var serializer = new Jackson2JsonRedisSerializer<>(PaymentMessageDto.class);
        serializer.setObjectMapper(objectMapper);
        adapter.setSerializer(serializer);
        return adapter;
    }

}
