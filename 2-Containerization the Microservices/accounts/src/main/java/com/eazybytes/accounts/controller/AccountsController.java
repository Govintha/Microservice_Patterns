package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.response.CustomerDto;
import com.eazybytes.accounts.dto.response.ErrorResponseDto;
import com.eazybytes.accounts.dto.response.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Account Microservices"
)
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private  IAccountService accountService;

    @Operation(
            summary = "Create Customer REST API ",
            description = "REST API to create new Customer for Bank "
    )
    @ApiResponse (
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        accountService.createAccount(customerDto);
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(    @Pattern(regexp = "^$|[0-9]{10}",message = "Account Number must be 10 digit")
                                                                @RequestParam String mobileNumber){

        CustomerDto customerDto = accountService.fetchCustomer(mobileNumber);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @ApiResponses ({

        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdate = accountService.updateCustomer(customerDto);

        if(isUpdate){
             return ResponseEntity
                     .status(HttpStatus.OK)
                     .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
             return ResponseEntity
                     .status(HttpStatus.BAD_REQUEST)
                     .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@Pattern(regexp = "^$|[0-9]{10}",message = "Account Number must be 10 digit")
                                                          @RequestParam String mobileNumber){

        boolean isDeleted = accountService.deleteCutomer(mobileNumber);

        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
