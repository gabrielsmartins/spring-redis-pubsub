package br.gasmartins.pubsub.application.web.controller.mapper;

import br.gasmartins.pubsub.application.web.controller.dto.OrderDto;
import br.gasmartins.pubsub.domain.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderControllerMapper {

    public OrderDto mapToDto(Order order) {
        var mapper = new ModelMapper();
        return mapper.map(order, OrderDto.class);
    }

    public Order mapToDomain(OrderDto orderDto) {
        var mapper = new ModelMapper();
        return mapper.map(orderDto, Order.class);
    }

}
