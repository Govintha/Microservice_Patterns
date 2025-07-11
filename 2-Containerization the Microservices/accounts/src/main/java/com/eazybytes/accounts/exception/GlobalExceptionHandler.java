package com.eazybytes.accounts.exception;


import com.eazybytes.accounts.dto.response.ErrorResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest request){

        ErrorResponseDto errorResponse=new ErrorResponseDto(

                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyException(CustomerAlreadyExistsException exception,
                                                                           WebRequest request){

         ErrorResponseDto errorResponse=new ErrorResponseDto(

                 request.getDescription(false),
                 HttpStatus.BAD_REQUEST,
                 exception.getMessage(),
                 LocalDateTime.now()
         );

         return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                           WebRequest request){

        ErrorResponseDto errorResponse=new ErrorResponseDto(

                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR );
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders header,HttpStatusCode statusCode,WebRequest request){

            Map<String,String> validationErrors=new HashMap<>();
            List<ObjectError> validationErrorsList=ex.getBindingResult().getAllErrors();

          validationErrorsList.forEach((error)->{
                      String fieldName=((FieldError)error).getField();
                      String validatedMessage=error.getDefaultMessage();
                      validationErrors.put(fieldName,validatedMessage);
                  }
                  );
            return  new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);

    }


}
