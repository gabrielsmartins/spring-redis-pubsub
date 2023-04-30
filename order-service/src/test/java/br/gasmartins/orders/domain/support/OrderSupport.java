package br.gasmartins.orders.domain.support;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.gasmartins.orders.domain.support.OrderItemSupport.defaultOrderItem;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderSupport {

    public static Order.OrderBuilder defaultOrder() {
        return Order.builder()
                .withId(UUID.randomUUID())
                .withCreatedAt(LocalDateTime.now())
                .withTotalDiscount(BigDecimal.ZERO)
                .withTotalAmount(BigDecimal.valueOf(500))
                .withFinishedAt(LocalDateTime.now())
                .withStatus(OrderStatus.CREATED)
                .withItems(List.of(defaultOrderItem().build()));
    }
}
