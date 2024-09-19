package com.frogdevelopment.pulsar;

import io.micronaut.pulsar.annotation.PulsarProducer;
import io.micronaut.pulsar.annotation.PulsarProducerClient;

@PulsarProducerClient
public interface Producer {

    @PulsarProducer(topic = "persistent://public/default/messages", producerName = "simple-j-producer")
    void sendBlocking(String message);
}
