package com.erykandbogdan.eventapp.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponseDto {

    private Integer status;
    private String code;
    private String message;
}
