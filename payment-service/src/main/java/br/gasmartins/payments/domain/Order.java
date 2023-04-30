package br.gasmartins.payments.domain;

import br.gasmartins.payments.domain.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    private UUID id;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderItem> items = new LinkedList<>();

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public Integer addItem(OrderItem item) {
        this.items.add(item);
        return this.items.size();
    }

    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class OrderItem {

        private UUID productId;
        private Integer quantity;
        private BigDecimal amount;

    }

}
