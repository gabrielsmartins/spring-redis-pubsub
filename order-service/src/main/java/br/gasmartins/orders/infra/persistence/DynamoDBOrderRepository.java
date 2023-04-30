package br.gasmartins.orders.infra.persistence;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.repository.OrderRepository;
import br.gasmartins.orders.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.infra.persistence.mapper.OrderPersistenceMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DynamoDBOrderRepository implements OrderRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public Order save(Order order) {
        var orderEntity = OrderPersistenceMapper.mapToEntity(order);
        this.dynamoDBMapper.save(orderEntity);
        order.setId(orderEntity.getId());
        return OrderPersistenceMapper.mapToDomain(orderEntity);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        var orderEntity = this.dynamoDBMapper.load(OrderEntity.class, id);
        return Optional.ofNullable(orderEntity).map(OrderPersistenceMapper::mapToDomain);
    }

}
