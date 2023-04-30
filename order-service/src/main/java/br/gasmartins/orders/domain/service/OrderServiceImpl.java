package br.gasmartins.orders.domain.service;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.Payment;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.exceptions.OrderNotFoundException;
import br.gasmartins.orders.domain.publisher.OrderPublisher;
import br.gasmartins.orders.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderPublisher publisher;

    @Override
    public Order create(Order order) {
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        return this.repository.save(order);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public Order submit(Order order) {
       this.publisher.publish(order);
       order.setStatus(OrderStatus.PENDING);
       return this.repository.save(order);
    }

    @Override
    public Order finish(Payment payment) {
        var orderId = payment.getOrderId();
        var order = this.findById(orderId)
                        .orElseThrow(() -> new OrderNotFoundException(String.format("Order not found for id %s", orderId)));
        order.setStatus(OrderStatus.fromCode(payment.getStatus().getCode()));
        order.setFinishedAt(LocalDateTime.now());
        return this.repository.save(order);
    }
}
