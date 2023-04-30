package br.gasmartins.orders.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    CREATED("C"),
    PENDING("P"),
    APPROVED("A"),
    REJECTED("R");

    private final String code;

    public static OrderStatus fromCode(String code){
        return Stream.of(OrderStatus.values())
                     .filter(status -> status.getCode().equals(code))
                     .findFirst()
                     .orElse(null);
    }

}
