package br.gasmartins.orders.infra.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@DynamoDBDocument
public class OrderItemEntity {

    @DynamoDBAttribute(attributeName = "product_id")
    private UUID productId;

    @DynamoDBAttribute(attributeName = "quantity")
    private Integer quantity;

    @DynamoDBAttribute(attributeName = "amount")
    private BigDecimal amount;

}
