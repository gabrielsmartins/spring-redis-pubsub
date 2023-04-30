package br.gasmartins.orders.infra.messaging.publisher.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.infra.messaging.publisher.dto.OrderMessageDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemMessagePublisherMapper {

    public static OrderMessageDto.OrderMessageItemDto mapToMessage(Order.OrderItem item) {
        return OrderMessageDto.OrderMessageItemDto.builder()
                .withProductId(item.getProductId())
                .withAmount(item.getAmount())
                .withQuantity(item.getQuantity())
                .build();
    }

}
