package com.frogdevelopment.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest(startApplication = false)
class MyItemRepositoryTest {

    @Inject
    MyItemRepository myItemRepository;

    @Test
    void should_updateByField1() {
        // given
        final var entity = new MyEntity(null, "my_field", 666);

        // when
        final var saved = myItemRepository.save(entity);

        // then
        assertThat(saved.id()).isEqualTo(1L);
        assertThat(saved.field1()).isEqualTo("my_field");
        assertThat(saved.field2()).isEqualTo(666);

        // given
        final var newEntity = new MyEntity(null, "my_field", 777);

        // when
        myItemRepository.updateByField1("my_field", newEntity);
        final var updated = myItemRepository.findById(saved.id());

        // then
        assertThat(updated).hasValueSatisfying(myEntity -> {
            assertThat(myEntity.id()).isEqualTo(1L);
            assertThat(myEntity.field1()).isEqualTo("my_field");
            assertThat(myEntity.field2()).isEqualTo(777);
        });
    }

}
