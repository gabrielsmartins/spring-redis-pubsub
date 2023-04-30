package br.gasmartins.orders.infra.messaging.publisher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class OrderMessageDto {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    @JsonProperty(value = "finished_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime finishedAt;

    @JsonProperty(value = "total_amount")
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount")
    private BigDecimal totalDiscount;

    @JsonProperty(value = "items")
    @Singular("addItem")
    private List<OrderMessageItemDto> items;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(setterPrefix = "with")
    public static class OrderMessageItemDto {

        @JsonProperty(value = "product_id")
        private UUID productId;

        @JsonProperty(value = "quantity")
        private Integer quantity;

        @JsonProperty(value = "amount")
        private BigDecimal amount;

    }

}