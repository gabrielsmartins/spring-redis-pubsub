package br.gasmartins.orders.application.messaging.subscriber.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMessageDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("order_id")
    private UUID orderId;

    @JsonProperty("status")
    private String status;

    @JsonProperty(value = "processed_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime processedAt;

    @JsonProperty(value = "total_amount")
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount")
    private BigDecimal totalDiscount;

}
