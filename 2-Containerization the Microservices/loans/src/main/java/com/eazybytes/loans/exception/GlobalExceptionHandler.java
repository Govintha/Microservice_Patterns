package com.eazybytes.loans.exception;

import com.eazybytes.loans.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

         Map<String,String> validationError=new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        allErrors.forEach((error)->{
            String field = ((FieldError) error).getField();
            String errorMessage=error.getDefaultMessage();
            validationError.put(field,errorMessage);

        });
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundException(ResourceNotFoundException ex,WebRequest request){

        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> loanAlreadyExistsException(LoanAlreadyExistsException ex,WebRequest request){

        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> genenalException(Exception ex,WebRequest request){

        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }
}


