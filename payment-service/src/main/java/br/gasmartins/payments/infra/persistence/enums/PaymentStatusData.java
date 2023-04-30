package br.gasmartins.payments.infra.persistence.enums;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
@DynamoDBTypeConvertedEnum
public enum PaymentStatusData {

    CREATED("C"),
    PENDING("P"),
    APPROVED("A"),
    REJECTED("R");

    private final String code;

    public static PaymentStatusData fromCode(String code){
        return Stream.of(PaymentStatusData.values())
                     .filter(statusData -> statusData.getCode().equals(code))
                     .findFirst()
                     .orElse(null);
    }


}
