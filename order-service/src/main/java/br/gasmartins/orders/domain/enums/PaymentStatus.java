package br.gasmartins.orders.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum PaymentStatus {

    APPROVED("A"),
    REJECTED("R");

    private final String code;

    public static PaymentStatus fromCode(String code){
        return Stream.of(PaymentStatus.values())
                .filter(status -> status.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
