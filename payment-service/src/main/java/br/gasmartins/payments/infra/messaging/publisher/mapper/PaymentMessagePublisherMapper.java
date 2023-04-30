package br.gasmartins.payments.infra.messaging.publisher.mapper;

import br.gasmartins.payments.domain.Payment;
import br.gasmartins.payments.infra.messaging.publisher.dto.PaymentMessageDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentMessagePublisherMapper {

    public static PaymentMessageDto mapToMessage(Payment payment) {
        return PaymentMessageDto.builder()
                                .withId(payment.getId())
                                .withOrderId(payment.getOrderId())
                                .withProcessedAt(payment.getProcessedAt())
                                .withStatus(payment.getStatus().getCode())
                                .withTotalAmount(payment.getTotalAmount())
                                .withTotalDiscount(payment.getTotalDiscount())
                                .build();
    }

}
