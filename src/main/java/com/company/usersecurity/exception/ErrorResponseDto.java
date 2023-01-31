package com.company.usersecurity.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {
    int status;
    String code;
    String message;
    String details;
    OffsetDateTime timeStamp;
    String path;
    Map<String,Object> data;
}
