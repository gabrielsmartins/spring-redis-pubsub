package br.gasmartins.orders.infra.messaging.publisher;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.publisher.OrderPublisher;
import br.gasmartins.orders.infra.messaging.publisher.dto.OrderMessageDto;
import br.gasmartins.orders.infra.messaging.publisher.mapper.OrderMessagePublisherMapper;
import br.gasmartins.orders.infra.messaging.config.RedisTopicProperties;
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
    private final RedisTopicProperties properties;

    @Override
    public void publish(Order order) {
        String topic = this.properties.getOrderTopic();
        var orderMessageDto = OrderMessagePublisherMapper.mapToMessage(order);
        log.info("Sending message: {}, {}", kv("topic", topic), kv("message", orderMessageDto));
        this.template.convertAndSend(topic, orderMessageDto);
        log.info("Message wast sent successfully: {}, {}", kv("topic", topic), kv("message", orderMessageDto));
    }

}
