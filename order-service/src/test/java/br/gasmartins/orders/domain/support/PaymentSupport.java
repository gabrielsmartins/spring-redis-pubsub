package br.gasmartins.orders.domain.support;

import br.gasmartins.orders.domain.Payment;
import br.gasmartins.orders.domain.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentSupport {

    public static Payment.PaymentBuilder defaultPayment() {
        return Payment.builder()
                .withId(UUID.randomUUID())
                .withOrderId(UUID.randomUUID())
                .withStatus(PaymentStatus.APPROVED)
                .withProcessedAt(LocalDateTime.now())
                .withTotalAmount(BigDecimal.TEN)
                .withTotalDiscount(BigDecimal.ZERO);
    }
}
