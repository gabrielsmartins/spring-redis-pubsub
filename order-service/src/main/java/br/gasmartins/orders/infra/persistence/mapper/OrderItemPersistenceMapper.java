package br.gasmartins.orders.infra.persistence.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.infra.persistence.entity.OrderItemEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemPersistenceMapper {

    public static OrderItemEntity mapToEntity(Order.OrderItem item) {
        return OrderItemEntity.builder()
                .withProductId(item.getProductId())
                .withAmount(item.getAmount())
                .withQuantity(item.getQuantity())
                .build();
    }

    public static Order.OrderItem mapToDomain(OrderItemEntity itemEntity) {
        return Order.OrderItem.builder()
                .withProductId(itemEntity.getProductId())
                .withAmount(itemEntity.getAmount())
                .withQuantity(itemEntity.getQuantity())
                .build();
    }

}
