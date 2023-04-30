package br.gasmartins.orders.application.messaging.subscriber.mapper;

import br.gasmartins.orders.application.messaging.subscriber.dto.PaymentMessageDto;
import br.gasmartins.orders.domain.Payment;
import br.gasmartins.orders.domain.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentMessageSubscriberMapper {

    public static Payment mapToDomain(PaymentMessageDto paymentMessageDto) {
        return Payment.builder()
                .withId(paymentMessageDto.getId())
                .withOrderId(paymentMessageDto.getOrderId())
                .withProcessedAt(paymentMessageDto.getProcessedAt())
                .withStatus(PaymentStatus.fromCode(paymentMessageDto.getStatus()))
                .withTotalAmount(paymentMessageDto.getTotalAmount())
                .withTotalDiscount(paymentMessageDto.getTotalDiscount())
                .build();
    }
}
