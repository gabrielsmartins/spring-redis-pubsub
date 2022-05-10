package br.gasmartins.pubsub.domain.repository;

import br.gasmartins.pubsub.domain.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);
    Optional<Order> findById(Long id);

}
