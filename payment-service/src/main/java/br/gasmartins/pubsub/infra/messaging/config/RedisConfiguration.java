package br.gasmartins.pubsub.infra.messaging.config;

import br.gasmartins.pubsub.application.messaging.subscriber.OrderMessageSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisTopicProperties.class)
public class RedisConfiguration {

    private final RedisTopicProperties properties;

    @Bean
    public RedisMessageListenerContainer redisContainer(MessageListenerAdapter adapter, RedisConnectionFactory connectionFactory) {
        var container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(adapter, new PatternTopic(properties.getOrderTopic()));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListener(OrderMessageSubscriber subscriber) {
        var adapter = new MessageListenerAdapter(subscriber);
        adapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        return adapter;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory){
        var template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
