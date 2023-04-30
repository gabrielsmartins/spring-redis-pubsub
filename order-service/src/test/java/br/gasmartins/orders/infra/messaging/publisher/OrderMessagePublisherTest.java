package br.gasmartins.orders.infra.messaging.publisher;


import br.gasmartins.orders.domain.support.OrderSupport;
import br.gasmartins.orders.infra.messaging.publisher.dto.OrderMessageDto;
import br.gasmartins.orders.infra.support.BaseIT;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
@ActiveProfiles("test")
public class OrderMessagePublisherTest extends BaseIT {

    @Autowired
    @InjectMocks
    private OrderMessagePublisher publisher;

    @SpyBean(name = "redisTemplate")
    private RedisTemplate<?, ?> template;

    private AutoCloseable autoCloseable;

    @BeforeAll
    public static void setupAll() {
        startRedis();
    }

    @BeforeEach
    public void setup() {
      this.autoCloseable = openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.autoCloseable.close();
    }

    @Test
    @DisplayName("Given Order When Publish Then Call Template")
    public void givenOrderWhenPublishThenCallTemplate() {
        var order = OrderSupport.defaultOrder().build();
        this.publisher.publish(order);

        verify(this.template, times(1)).convertAndSend(anyString(), any(OrderMessageDto.class));
    }




}