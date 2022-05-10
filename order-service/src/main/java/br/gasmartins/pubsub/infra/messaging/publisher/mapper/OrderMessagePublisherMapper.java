package br.gasmartins.pubsub.infra.messaging.publisher.mapper;

import br.gasmartins.pubsub.domain.Order;
import br.gasmartins.pubsub.infra.messaging.publisher.dto.OrderMessageDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagePublisherMapper {

    public OrderMessageDto mapToMessage(Order order){
        return null;
    }

}
