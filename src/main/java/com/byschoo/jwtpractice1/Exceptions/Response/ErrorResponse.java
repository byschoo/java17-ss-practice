package com.byschoo.jwtpractice1.Exceptions.Response;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final String message;
    private Map<String, String> mapError;
    private final String code;
    @JsonProperty("exception")
    private Object object;
    private final String severity;
    private final String url;
    private final String method;
    private final LocalDateTime dateTime;

}
