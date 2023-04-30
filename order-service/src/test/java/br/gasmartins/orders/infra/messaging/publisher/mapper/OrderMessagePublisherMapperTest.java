package br.gasmartins.orders.infra.messaging.publisher.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.domain.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderMessagePublisherMapperTest {

    @Test
    @DisplayName("Given Order When Map Then Return Order Message Dto")
    public void givenOrderWhenMapThenReturnOrderMessageDto() {

        var order = defaultOrder().build();
        var orderMessageDto = OrderMessagePublisherMapper.mapToMessage(order);

        assertThat(orderMessageDto).isNotNull();
        assertThat(orderMessageDto).hasNoNullFieldsOrProperties();
        assertThat(orderMessageDto.getId()).isEqualTo(order.getId());
        assertThat(orderMessageDto.getStatus()).isEqualTo(order.getStatus().getCode());
        assertThat(orderMessageDto.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(orderMessageDto.getFinishedAt()).isEqualTo(order.getFinishedAt());
        assertThat(orderMessageDto.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderMessageDto.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(orderMessageDto.getItems().size()).isEqualTo(order.getItems().size());
    }

}