package br.gasmartins.orders.infra.messaging.publisher.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.domain.support.OrderItemSupport.defaultOrderItem;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemMessagePublisherMapperTest {

    @Test
    @DisplayName("Given Item When Map Then Return Item Dto")
    public void givenItemWhenMapThenReturnItemDto() {
        var orderItem = defaultOrderItem().build();
        var orderMessageItemDto = OrderItemMessagePublisherMapper.mapToMessage(orderItem);

        assertThat(orderMessageItemDto).isNotNull();
        assertThat(orderMessageItemDto).hasNoNullFieldsOrProperties();
        assertThat(orderMessageItemDto.getProductId()).isEqualTo(orderItem.getProductId());
        assertThat(orderMessageItemDto.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(orderMessageItemDto.getAmount()).isEqualTo(orderItem.getAmount());
    }

}