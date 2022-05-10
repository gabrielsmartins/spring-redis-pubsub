package br.gasmartins.pubsub.domain.service;

import br.gasmartins.pubsub.domain.Order;
import br.gasmartins.pubsub.domain.enums.Status;
import br.gasmartins.pubsub.domain.publisher.OrderPublisher;
import br.gasmartins.pubsub.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderPublisher publisher;

    @Override
    public Order create(Order order) {
        order.setStatus(Status.CREATED);
        return this.repository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order submit(Order order) {
       this.publisher.publish(order);
       order.setStatus(Status.PENDING);
       return this.repository.save(order);
    }

    @Override
    public Order finish(Order order) {
        return null;
    }
}
