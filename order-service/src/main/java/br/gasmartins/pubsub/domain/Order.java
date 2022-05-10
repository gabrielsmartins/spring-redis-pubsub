package br.gasmartins.pubsub.domain;

import br.gasmartins.pubsub.domain.enums.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
public class Order {

    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    private final List<OrderItem> items = new LinkedList<>();

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public Integer addItem(OrderItem item){
        this.items.add(item);
        return this.items.size();
    }

    public Integer removeItem(OrderItem item){
        this.items.remove(item);
        return this.items.size();
    }

    @Data
    public static class OrderItem {

        private UUID productId;
        private Integer quantity;
        private BigDecimal amount;

    }

}
