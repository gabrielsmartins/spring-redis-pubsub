package br.gasmartins.payments.domain.service;

import br.gasmartins.payments.domain.Order;
import br.gasmartins.payments.domain.Payment;
import br.gasmartins.payments.domain.enums.PaymentStatus;
import br.gasmartins.payments.domain.publisher.PaymentPublisher;
import br.gasmartins.payments.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProcessPaymentServiceImpl implements ProcessPaymentService {

    private final PaymentRepository repository;
    private final PaymentPublisher publisher;

    @Override
    public void process(Order order) {
        var payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setProcessedAt(LocalDateTime.now());
        payment.setTotalAmount(order.getTotalAmount());
        payment.setTotalDiscount(order.getTotalDiscount());
        //Do some validations and approve or reject ...
        payment.setStatus(PaymentStatus.APPROVED);
        var savedPayment = this.repository.save(payment);
        this.publisher.publish(savedPayment);
    }

}
