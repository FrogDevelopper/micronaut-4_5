package com.frogdevelopment.pulsar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.frogdevelopment.MyItem;

import io.micronaut.context.annotation.Requires;
import io.micronaut.pulsar.MessageSchema;
import io.micronaut.pulsar.annotation.PulsarReader;
import io.micronaut.pulsar.annotation.PulsarReaderClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import reactor.core.publisher.Mono;

@Tag("integrationTest")
@MicronautTest(startApplication = false, environments = "pulsar")
class MessageBrokerIntegrationTest {

    @Singleton
    @PulsarReaderClient
    @Requires(env = "pulsar")
    static class PulsarMessageConsumer {
        private final Reader<String> reader;

        PulsarMessageConsumer(
                @PulsarReader(
                        schema = MessageSchema.STRING,
                        topic = "persistent://public/default/messages",
                        readerName = "simple-j-reader",
                        readTimeout = 5)
                Reader<String> reader) {
            this.reader = reader;
        }

        String poll() throws PulsarClientException {
            return reader.readNext().getValue();
        }

        void clear() throws PulsarClientException {
            reader.seek(System.currentTimeMillis());
        }
    }

    @Inject
    Producer producer;
    @Inject
    private PulsarMessageConsumer pulsarConsumer;

    @BeforeEach
    void beforeEach() throws Exception {
        pulsarConsumer.clear();
    }

    @Test
    void should_works() throws PulsarClientException {
        // when
        producer.sendBlocking("hello world");

        // then
        var publishedPulsarEvent = pulsarConsumer.poll();
        assertThat(publishedPulsarEvent).isEqualTo("hello world");
    }
}
