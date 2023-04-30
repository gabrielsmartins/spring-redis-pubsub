package br.gasmartins.orders.domain.repository;

import br.gasmartins.orders.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);
    Optional<Order> findById(UUID id);

}
