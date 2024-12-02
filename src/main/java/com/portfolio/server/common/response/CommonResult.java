package com.portfolio.server.common.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommonResult {

    private String status;
    private int code;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
