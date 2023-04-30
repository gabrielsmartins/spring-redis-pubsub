package br.gasmartins.payments.application.messaging.subscriber.mapper;

import br.gasmartins.payments.application.messaging.subscriber.dto.OrderMessageDto;
import br.gasmartins.payments.domain.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemMessageSubscriberMapper {

    public static Order.OrderItem mapToDomain(OrderMessageDto.OrderMessageItemDto orderMessageItemDto) {
        return Order.OrderItem.builder()
                            .withProductId(orderMessageItemDto.getProductId())
                            .withAmount(orderMessageItemDto.getAmount())
                            .withQuantity(orderMessageItemDto.getQuantity())
                            .build();
    }
}
