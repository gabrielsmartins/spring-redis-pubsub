package br.gasmartins.pubsub.domain.service;

import br.gasmartins.pubsub.domain.Order;

import java.util.Optional;

public interface OrderService {

    Order create(Order order);
    Optional<Order> findById(Long id);
    Order submit(Order order);
    Order finish(Order order);
}
