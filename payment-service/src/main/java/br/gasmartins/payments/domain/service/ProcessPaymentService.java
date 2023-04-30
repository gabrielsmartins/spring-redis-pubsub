package br.gasmartins.payments.domain.service;

import br.gasmartins.payments.domain.Order;

public interface ProcessPaymentService {

    void process(Order order);

}
