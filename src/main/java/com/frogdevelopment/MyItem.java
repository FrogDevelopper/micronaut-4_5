package com.frogdevelopment;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record MyItem(String field1, Integer field2) {
}
