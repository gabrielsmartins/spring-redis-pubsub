package br.gasmartins.pubsub.domain;

import br.gasmartins.pubsub.domain.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
public class Payment {

    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

}
