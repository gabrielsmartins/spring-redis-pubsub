package br.gasmartins.orders.application.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private String status;

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    @JsonProperty(value = "finished_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime finishedAt;

    @JsonProperty(value = "total_amount")
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount")
    private BigDecimal totalDiscount;

    @JsonProperty(value = "items")
    @Singular("addItem")
    private List<OrderItemDto> items;

    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class OrderItemDto {

        @JsonProperty(value = "product_id")
        private UUID productId;

        @JsonProperty(value = "quantity")
        private Integer quantity;

        @JsonProperty(value = "amount")
        private BigDecimal amount;

    }

}