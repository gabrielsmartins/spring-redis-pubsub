package br.gasmartins.orders.application.web.controller.mapper;

import br.gasmartins.orders.application.web.controller.dto.OrderDto;
import br.gasmartins.orders.domain.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemControllerMapper {

    public static Order.OrderItem mapToDomain(OrderDto.OrderItemDto itemDto) {
        return Order.OrderItem.builder()
                        .withProductId(itemDto.getProductId())
                        .withAmount(itemDto.getAmount())
                        .withQuantity(itemDto.getQuantity())
                        .build();
    }


    public static OrderDto.OrderItemDto mapToDto(Order.OrderItem item) {
        return OrderDto.OrderItemDto.builder()
                .withProductId(item.getProductId())
                .withAmount(item.getAmount())
                .withQuantity(item.getQuantity())
                .build();
    }

}
