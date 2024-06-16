package com.store.fake.proxy.response.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record GeolocationResponse(String lat,
                                  @JsonProperty("long")
                                  String longi) {
}
