package com.kupreychik.userservice.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private HttpStatus status;
    private String message;
    private String details;
}
