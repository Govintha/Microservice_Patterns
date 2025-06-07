package com.eazybytes.card.card.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(String apiPath,
                               HttpStatus errorCode ,
                               String errorMessage,
                               LocalDateTime errorTime) {
}
