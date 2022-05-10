package br.gasmartins.pubsub.application.messaging.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderMessageSubscriber implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Receiving message: {}, {}", kv("message", message), kv("pattern", pattern));
    }

}
