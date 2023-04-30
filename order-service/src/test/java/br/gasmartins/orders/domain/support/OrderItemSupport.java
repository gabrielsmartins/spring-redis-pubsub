package br.gasmartins.orders.domain.support;

import br.gasmartins.orders.domain.Order.OrderItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemSupport {

    public static OrderItem.OrderItemBuilder defaultOrderItem() {
        return OrderItem.builder()
                .withProductId(UUID.randomUUID())
                .withAmount(BigDecimal.TEN)
                .withQuantity(1);
    }

}
