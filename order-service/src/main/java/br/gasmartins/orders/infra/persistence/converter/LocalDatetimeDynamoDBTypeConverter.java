package br.gasmartins.orders.infra.persistence.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDatetimeDynamoDBTypeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    @Override
    public String convert(LocalDateTime object) {
        return object == null ? null : object.format(FORMATTER);
    }

    @Override
    public LocalDateTime unconvert(String object) {
        return object == null ? null : LocalDateTime.parse(object, FORMATTER);
    }

}
