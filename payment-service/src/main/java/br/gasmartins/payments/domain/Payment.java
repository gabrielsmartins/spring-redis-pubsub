package br.gasmartins.payments.domain;

import br.gasmartins.payments.domain.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class Payment {

    private UUID id;
    private UUID orderId;
    private PaymentStatus status;
    private LocalDateTime processedAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

}
