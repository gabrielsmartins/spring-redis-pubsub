package br.gasmartins.payments.domain.repository;

import br.gasmartins.payments.domain.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);

}
