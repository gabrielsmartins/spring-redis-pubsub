package br.gasmartins.pubsub.domain.publisher;

import br.gasmartins.pubsub.domain.Order;

public interface OrderPublisher {

    void publish(Order order);

}
