package br.gasmartins.payments.application.messaging.subscriber;

import br.gasmartins.payments.application.messaging.subscriber.dto.OrderMessageDto;
import br.gasmartins.payments.application.messaging.subscriber.mapper.OrderMessageSubscriberMapper;
import br.gasmartins.payments.domain.service.ProcessPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderMessageSubscriber {

    private final ProcessPaymentService service;

    public void handleMessage(OrderMessageDto orderMessageDto) {
        log.info("Receiving message: {}", kv("message", orderMessageDto));

        log.info("Mapping order: {}", kv("message", orderMessageDto));
        var order = OrderMessageSubscriberMapper.mapToDomain(orderMessageDto);

        log.info("Processing order: {}", kv("order", order));
        this.service.process(order);
        log.info("Order was processed successfully: {}", kv("order", order));
    }

}
