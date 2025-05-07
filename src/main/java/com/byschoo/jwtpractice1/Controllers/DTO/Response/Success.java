package com.byschoo.jwtpractice1.Controllers.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Success {

    private String message;
    private Object object;

}
