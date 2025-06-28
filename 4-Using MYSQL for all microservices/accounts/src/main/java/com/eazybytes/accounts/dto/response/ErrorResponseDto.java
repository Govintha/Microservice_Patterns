package com.eazybytes.accounts.dto.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(String apiPath,
                               HttpStatus errorCode,
                               String errorMessage,
                               LocalDateTime errorTime) {
}
