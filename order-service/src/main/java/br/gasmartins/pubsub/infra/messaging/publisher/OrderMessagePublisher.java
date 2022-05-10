package br.gasmartins.pubsub.infra.messaging.publisher;

import br.gasmartins.pubsub.domain.Order;
import br.gasmartins.pubsub.domain.publisher.OrderPublisher;
import br.gasmartins.pubsub.infra.messaging.config.RedisTopicProperties;
import br.gasmartins.pubsub.infra.messaging.publisher.dto.OrderMessageDto;
import br.gasmartins.pubsub.infra.messaging.publisher.mapper.OrderMessagePublisherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisTopicProperties.class)
@Slf4j
public class OrderMessagePublisher implements OrderPublisher {

    private final RedisTemplate<Long, OrderMessageDto> template;
    private final OrderMessagePublisherMapper mapper;
    private final RedisTopicProperties properties;

    @Override
    public void publish(Order order) {
        String topic = this.properties.getOrderTopic();
        var orderMessageDto = this.mapper.mapToMessage(order);
        log.info("Sending message: {}, {}", kv("topic", topic), kv("message", orderMessageDto));
        this.template.convertAndSend(topic, orderMessageDto);
        log.info("Message wast sent successfully: {}, {}", kv("topic", topic), kv("message", orderMessageDto));
    }

}
