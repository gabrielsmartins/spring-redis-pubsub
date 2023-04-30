package br.gasmartins.orders.infra.persistence.enums;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
@DynamoDBTypeConvertedEnum
public enum StatusData {

    CREATED("C"),
    PENDING("P"),
    APPROVED("A"),
    REJECTED("R");

    private final String code;

    public static StatusData fromCode(String code){
        return Stream.of(StatusData.values())
                     .filter(statusData -> statusData.getCode().equals(code))
                     .findFirst()
                     .orElse(null);
    }


}
