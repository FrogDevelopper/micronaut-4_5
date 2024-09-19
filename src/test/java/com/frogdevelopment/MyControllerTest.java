package com.frogdevelopment;

import static io.micronaut.http.HttpStatus.OK;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.data.model.Page;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
class MyControllerTest {

    @Inject
    @Client(MyController.BASE_ENDPOINT)
    private HttpClient client;

    @Test
    void should_getEmptyPageable_when_searchingWithNonMatchingCriteria() {
        // Given

        // When
        var response = client.toBlocking().exchange(HttpRequest.GET("/search"), Page.class);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(OK.getCode());
        assertThat(response.getBody()).isPresent();
        assertThat(response.getBody().get()).isEmpty();
    }

}
