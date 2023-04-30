package br.gasmartins.payments.infra.persistence;

import br.gasmartins.payments.domain.Payment;
import br.gasmartins.payments.domain.repository.PaymentRepository;
import br.gasmartins.payments.infra.persistence.mapper.PaymentPersistenceMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DynamoDBPaymentRepository implements PaymentRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public Payment save(Payment payment) {
        var paymentEntity = PaymentPersistenceMapper.mapToEntity(payment);
        this.dynamoDBMapper.save(paymentEntity);
        payment.setId(paymentEntity.getId());
        return PaymentPersistenceMapper.mapToDomain(paymentEntity);
    }

}
