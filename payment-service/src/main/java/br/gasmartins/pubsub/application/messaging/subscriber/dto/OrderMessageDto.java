package br.gasmartins.pubsub.application.messaging.subscriber.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
public class OrderMessageDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "finished_at")
    private LocalDateTime finishedAt;

    @JsonProperty(value = "total_amount")
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount")
    private BigDecimal totalDiscount;

    @JsonProperty(value = "items")
    @Singular
    private List<OrderMessageItemDto> items;

    @Data
    public static class OrderMessageItemDto {

        @JsonProperty(value = "product_id")
        private UUID productId;

        @JsonProperty(value = "quantity")
        private Integer quantity;

        @JsonProperty(value = "amount")
        private BigDecimal amount;

    }

}