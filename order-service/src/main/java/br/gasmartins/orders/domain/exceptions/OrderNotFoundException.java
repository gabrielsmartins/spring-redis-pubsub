package br.gasmartins.orders.domain.exceptions;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String message) {
        super(message);
    }

}
