package br.gasmartins.pubsub.infra.persistence.adapter;

import br.gasmartins.pubsub.domain.Order;
import br.gasmartins.pubsub.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderPersistenceAdapter implements OrderRepository {

    @Override
    public Order save(Order order) {
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }
}
