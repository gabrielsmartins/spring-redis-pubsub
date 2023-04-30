package br.gasmartins.orders.application.web.support;

import br.gasmartins.orders.application.web.controller.dto.OrderDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemDtoSupport {

    public static OrderDto.OrderItemDto.OrderItemDtoBuilder defaultOrderItemDto() {
        return OrderDto.OrderItemDto.builder()
                .withProductId(UUID.randomUUID())
                .withAmount(BigDecimal.TEN)
                .withQuantity(1);
    }

}
