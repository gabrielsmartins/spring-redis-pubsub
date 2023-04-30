package br.gasmartins.payments.domain.publisher;

import br.gasmartins.payments.domain.Payment;

public interface PaymentPublisher {

    void publish(Payment payment);

}
