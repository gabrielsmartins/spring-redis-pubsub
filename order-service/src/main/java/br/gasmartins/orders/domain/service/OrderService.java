package br.gasmartins.orders.domain.service;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.Payment;

import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    Order create(Order order);
    Optional<Order> findById(UUID id);
    Order submit(Order order);
    Order finish(Payment payment);
}
