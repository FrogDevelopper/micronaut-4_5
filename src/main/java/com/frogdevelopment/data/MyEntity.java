package com.frogdevelopment.data;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.micronaut.serde.annotation.Serdeable;

@Entity
@Serdeable
@Table(name = "entities")
public record MyEntity(
        @Id @GeneratedValue(strategy = IDENTITY) Long id,
        String field1,
        Integer field2
) {
}
