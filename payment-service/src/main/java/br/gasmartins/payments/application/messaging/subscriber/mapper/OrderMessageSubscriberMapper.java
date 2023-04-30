package br.gasmartins.payments.application.messaging.subscriber.mapper;

import br.gasmartins.payments.application.messaging.subscriber.dto.OrderMessageDto;
import br.gasmartins.payments.domain.Order;
import br.gasmartins.payments.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMessageSubscriberMapper {

    public static Order mapToDomain(OrderMessageDto orderMessageDto) {
        var order = Order.builder()
                        .withId(orderMessageDto.getId())
                        .withCreatedAt(orderMessageDto.getCreatedAt())
                        .withFinishedAt(orderMessageDto.getFinishedAt())
                        .withStatus(OrderStatus.fromCode(orderMessageDto.getStatus()))
                        .withTotalAmount(orderMessageDto.getTotalAmount())
                        .withTotalDiscount(orderMessageDto.getTotalDiscount())
                        .build();
        orderMessageDto.getItems()
                       .stream()
                       .map(OrderItemMessageSubscriberMapper::mapToDomain)
                       .forEach(order::addItem);
        return order;
    }

}
