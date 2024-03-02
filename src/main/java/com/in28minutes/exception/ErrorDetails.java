package com.in28minutes.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private String details;
    private LocalDate timestamp;
}
