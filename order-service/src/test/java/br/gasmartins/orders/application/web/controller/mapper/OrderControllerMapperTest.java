package br.gasmartins.orders.application.web.controller.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.application.web.support.OrderDtoSupport.defaultOrderDto;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderControllerMapperTest {

    @Test
    @DisplayName("Given Order Dto When Map Then Return Order")
    public void givenOrderDtoWhenMapThenReturnOrder() {

        var orderDto = defaultOrderDto().build();
        var order = OrderControllerMapper.mapToDomain(orderDto);

        assertThat(order).isNotNull();
        assertThat(order).isNotNull();
        assertThat(order).hasNoNullFieldsOrProperties();
        assertThat(order.getId()).isEqualTo(orderDto.getId());
        assertThat(order.getStatus().getCode()).isEqualTo(orderDto.getStatus());
        assertThat(order.getCreatedAt()).isEqualTo(orderDto.getCreatedAt());
        assertThat(order.getFinishedAt()).isEqualTo(orderDto.getFinishedAt());
        assertThat(order.getTotalAmount()).isEqualTo(orderDto.getTotalAmount());
        assertThat(order.getTotalDiscount()).isEqualTo(orderDto.getTotalDiscount());
        assertThat(order.getItems().size()).isEqualTo(orderDto.getItems().size());
    }

}