package br.gasmartins.orders.infra.persistence.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.infra.persistence.enums.StatusData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderPersistenceMapper {

    public static OrderEntity mapToEntity(Order order){
        var itemEntities = order.getItems()
                                .stream()
                                .map(OrderItemPersistenceMapper::mapToEntity)
                                .collect(Collectors.toList());
        return OrderEntity.builder()
                              .withId(order.getId())
                              .withCreatedAt(order.getCreatedAt())
                              .withFinishedAt(order.getFinishedAt())
                              .withStatus(StatusData.fromCode(order.getStatus().getCode()))
                              .withTotalAmount(order.getTotalAmount())
                              .withTotalDiscount(order.getTotalDiscount())
                              .withItems(itemEntities)
                              .build();
    }

    public static Order mapToDomain(OrderEntity orderEntity){
        var order = Order.builder()
                .withId(orderEntity.getId())
                .withCreatedAt(orderEntity.getCreatedAt())
                .withFinishedAt(orderEntity.getFinishedAt())
                .withStatus(OrderStatus.fromCode(orderEntity.getStatus().getCode()))
                .withTotalAmount(orderEntity.getTotalAmount())
                .withTotalDiscount(orderEntity.getTotalDiscount())
                .build();
        orderEntity.getItems()
                   .stream()
                   .map(OrderItemPersistenceMapper::mapToDomain)
                   .forEach(order::addItem);
        return order;
    }

}
