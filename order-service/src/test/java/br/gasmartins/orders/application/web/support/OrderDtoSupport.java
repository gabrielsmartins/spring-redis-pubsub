package br.gasmartins.orders.application.web.support;

import br.gasmartins.orders.application.web.controller.dto.OrderDto;
import br.gasmartins.orders.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDtoSupport {

    public static OrderDto.OrderDtoBuilder defaultOrderDto() {
        return OrderDto.builder()
                .withId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withTotalDiscount(BigDecimal.ZERO)
                .withTotalAmount(BigDecimal.valueOf(500))
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.CREATED.getCode())
                .withAddItem(OrderItemDtoSupport.defaultOrderItemDto().build());
    }
}
