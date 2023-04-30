package br.gasmartins.orders.domain.service;


import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.exceptions.OrderNotFoundException;
import br.gasmartins.orders.domain.publisher.OrderPublisher;
import br.gasmartins.orders.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static br.gasmartins.orders.domain.support.OrderSupport.defaultOrder;
import static br.gasmartins.orders.domain.support.PaymentSupport.defaultPayment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    private OrderService service;
    private OrderRepository repository;
    private OrderPublisher publisher;

    @BeforeEach
    public void setup() {
        this.repository = mock(OrderRepository.class);
        this.publisher = mock(OrderPublisher.class);
        this.service = new OrderServiceImpl(this.repository, this.publisher);
    }

    @Test
    @DisplayName("Given Order When Create Then Return Created Order")
    public void givenOrderWhenCreateThenReturnCreatedOrder() {
        var order = defaultOrder().build();

        when(this.repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var createdOrder = this.service.create(order);

        assertThat(createdOrder).isNotNull();
        assertThat(createdOrder.getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(createdOrder.getCreatedAt()).isNotNull();
        verify(this.repository, times(1)).save(createdOrder);
    }

    @Test
    @DisplayName("Given Payment When Order Not Exists Then Throw Exception")
    public void givenPaymentWhenOrderNotExistsThenThenThrowException() {

        when(this.repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        var payment = defaultPayment().build();

        assertThatThrownBy(() -> this.service.finish(payment)).isInstanceOf(OrderNotFoundException.class);

        verify(this.repository, never()).save(any(Order.class));
    }

    @Test
    @DisplayName("Given Payment When Order Exists Then Return Finished Order")
    public void givenPaymentWhenOrderExistsThenReturnCreatedOrder() {
        var order = defaultOrder().build();
        when(this.repository.findById(any(UUID.class))).thenReturn(Optional.of(order));
        when(this.repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var payment = defaultPayment().build();

        var finished = this.service.finish(payment);

        assertThat(finished).isNotNull();
        assertThat(finished.getStatus().getCode()).isEqualTo(payment.getStatus().getCode());
        assertThat(finished.getFinishedAt()).isNotNull();
        verify(this.repository, times(1)).save(finished);
    }


    @Test
    @DisplayName("Given Order When Submit Then Return Submitted Order")
    public void givenOrderWhenSubmitThenReturnSubmittedOrder() {
        var order = defaultOrder().build();

        when(this.repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var submitted = this.service.submit(order);

        assertThat(submitted).isNotNull();
        assertThat(submitted.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(submitted.getFinishedAt()).isNotNull();
        verify(this.repository, times(1)).save(submitted);
        verify(this.publisher, times(1)).publish(submitted);
    }

    @Test
    @DisplayName("Given Order When Exists Then Return Existing Order")
    public void givenOrderWhenExistsThenReturnExistingOrder() {
        var order = defaultOrder().build();

        when(this.repository.findById(any(UUID.class))).thenReturn(Optional.of(order));

        var orderId = UUID.randomUUID();
        var optionalOrder = this.service.findById(orderId);

        assertThat(optionalOrder).isPresent();
        verify(this.repository, times(1)).findById(orderId);
    }

}