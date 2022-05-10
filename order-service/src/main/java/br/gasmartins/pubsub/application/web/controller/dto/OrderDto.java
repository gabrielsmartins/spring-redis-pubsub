package br.gasmartins.pubsub.application.web.controller.dto;

import br.gasmartins.pubsub.domain.enums.Status;
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
public class OrderDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private Status status;

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(value = "finished_at", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime finishedAt;

    @JsonProperty(value = "total_amount")
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount")
    private BigDecimal totalDiscount;

    @JsonProperty(value = "items")
    @Singular
    private List<OrderItemDto> items;

    @Data
    public static class OrderItemDto {

        @JsonProperty(value = "product_id")
        private UUID productId;

        @JsonProperty(value = "quantity")
        private Integer quantity;

        @JsonProperty(value = "amount")
        private BigDecimal amount;

    }

}