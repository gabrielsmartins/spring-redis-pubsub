package br.gasmartins.payments.infra.persistence.mapper;

import br.gasmartins.payments.domain.Payment;
import br.gasmartins.payments.domain.enums.PaymentStatus;
import br.gasmartins.payments.infra.persistence.entity.PaymentEntity;
import br.gasmartins.payments.infra.persistence.enums.PaymentStatusData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentPersistenceMapper {

    public static PaymentEntity mapToEntity(Payment payment) {
        return PaymentEntity.builder()
                            .withId(payment.getId())
                            .withOrderId(payment.getOrderId())
                            .withStatus(PaymentStatusData.fromCode(payment.getStatus().getCode()))
                            .withProcessedAt(payment.getProcessedAt())
                            .withTotalAmount(payment.getTotalAmount())
                            .withTotalDiscount(payment.getTotalDiscount())
                            .build();
    }


    public static Payment mapToDomain(PaymentEntity paymentEntity) {
        return Payment.builder()
                      .withId(paymentEntity.getId())
                      .withOrderId(paymentEntity.getOrderId())
                      .withStatus(PaymentStatus.fromCode(paymentEntity.getStatus().getCode()))
                      .withProcessedAt(paymentEntity.getProcessedAt())
                      .withTotalAmount(paymentEntity.getTotalAmount())
                      .withTotalDiscount(paymentEntity.getTotalDiscount())
                      .build();
    }

}
