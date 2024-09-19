package com.frogdevelopment;

import static io.micronaut.http.HttpResponse.ok;
import static io.micronaut.http.HttpStatus.OK;
import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.serde.annotation.Serdeable;

@Controller(MyController.BASE_ENDPOINT)
@ExecuteOn(BLOCKING)
public class MyController {

    public static final String BASE_ENDPOINT = "/items";

    @Status(OK)
    @Get("/search")
    public HttpResponse<Page<MyItem>> searchCampaigns(Pageable pageable) {
        return ok(Page.empty());
    }

    @Serdeable
    public record MyItem(String field1, Integer field2) {
    }
}
