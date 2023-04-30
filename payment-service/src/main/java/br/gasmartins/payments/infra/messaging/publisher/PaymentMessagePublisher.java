package br.gasmartins.payments.infra.messaging.publisher;

import br.gasmartins.payments.domain.Payment;
import br.gasmartins.payments.domain.publisher.PaymentPublisher;
import br.gasmartins.payments.infra.messaging.config.RedisTopicProperties;
import br.gasmartins.payments.infra.messaging.publisher.dto.PaymentMessageDto;
import br.gasmartins.payments.infra.messaging.publisher.mapper.PaymentMessagePublisherMapper;
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
public class PaymentMessagePublisher implements PaymentPublisher {

    private final RedisTemplate<Long, PaymentMessageDto> template;
    private final RedisTopicProperties properties;

    @Override
    public void publish(Payment payment) {
        String topic = this.properties.getPaymentTopic();
        var paymentMessageDto = PaymentMessagePublisherMapper.mapToMessage(payment);
        log.info("Sending message: {}, {}", kv("topic", topic), kv("message", paymentMessageDto));
        this.template.convertAndSend(topic, paymentMessageDto);
        log.info("Message wast sent successfully: {}, {}", kv("topic", topic), kv("message", paymentMessageDto));
    }

}
