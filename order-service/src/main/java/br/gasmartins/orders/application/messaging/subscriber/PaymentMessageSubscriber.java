package br.gasmartins.orders.application.messaging.subscriber;

import br.gasmartins.orders.application.messaging.subscriber.dto.PaymentMessageDto;
import br.gasmartins.orders.application.messaging.subscriber.mapper.PaymentMessageSubscriberMapper;
import br.gasmartins.orders.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentMessageSubscriber {

    private final OrderService service;

    public void handleMessage(PaymentMessageDto paymentMessageDto) {
        log.info("Receiving message: {}", kv("message", paymentMessageDto));

        log.info("Mapping payment: {}", kv("message", paymentMessageDto));
        var payment = PaymentMessageSubscriberMapper.mapToDomain(paymentMessageDto);

        log.info("Processing payment: {}", kv("payment", payment));
        this.service.finish(payment);
        log.info("Payment was processed successfully: {}", kv("payment", payment));
    }

}
