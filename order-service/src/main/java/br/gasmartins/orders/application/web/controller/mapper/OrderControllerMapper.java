package br.gasmartins.orders.application.web.controller.mapper;

import br.gasmartins.orders.application.web.controller.dto.OrderDto;
import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderControllerMapper {

    public static OrderDto mapToDto(Order order) {
        var itemsDto = order.getItems()
                .stream()
                .map(OrderItemControllerMapper::mapToDto)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return OrderDto.builder()
                .withId(order.getId())
                .withCreatedAt(order.getCreatedAt())
                .withFinishedAt(order.getFinishedAt())
                .withStatus(order.getStatus().getCode())
                .withTotalAmount(order.getTotalAmount())
                .withTotalDiscount(order.getTotalDiscount())
                .withItems(itemsDto)
                .build();
    }

    public static Order mapToDomain(OrderDto orderDto) {
        var order = Order.builder()
                         .withId(orderDto.getId())
                         .withCreatedAt(orderDto.getCreatedAt())
                         .withFinishedAt(orderDto.getFinishedAt())
                         .withStatus(OrderStatus.fromCode(orderDto.getStatus()))
                         .withTotalAmount(orderDto.getTotalAmount())
                         .withTotalDiscount(orderDto.getTotalDiscount())
                         .build();
         orderDto.getItems()
                 .stream()
                 .map(OrderItemControllerMapper::mapToDomain)
                 .forEach(order::addItem);
        return order;
    }

}
