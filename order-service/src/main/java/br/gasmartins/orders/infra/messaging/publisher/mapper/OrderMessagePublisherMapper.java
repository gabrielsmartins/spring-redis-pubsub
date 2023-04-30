package br.gasmartins.orders.infra.messaging.publisher.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.infra.messaging.publisher.dto.OrderMessageDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMessagePublisherMapper {

    public static OrderMessageDto mapToMessage(Order order){
        var itemsDto = order.getItems()
                            .stream()
                            .map(OrderItemMessagePublisherMapper::mapToMessage)
                            .collect(Collectors.toCollection(LinkedHashSet::new));
        return OrderMessageDto.builder()
                              .withId(order.getId())
                              .withCreatedAt(order.getCreatedAt())
                              .withFinishedAt(order.getFinishedAt())
                              .withStatus(order.getStatus().getCode())
                              .withTotalAmount(order.getTotalAmount())
                              .withTotalDiscount(order.getTotalDiscount())
                              .withItems(itemsDto)
                              .build();
    }

}
