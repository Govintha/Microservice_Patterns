package com.eazybytes.accounts.dto.response;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record AccountsDto(

        @NotEmpty(message = "AccountType can not empty or null")
        String accountType,
        @NotEmpty(message = "Branch Address cannot be null or empty")
        String branchAddress,
        @NotEmpty(message = "Account number con not be empty or null")
        @Pattern(regexp = "^$|[0-9]{10}",message = "Account Number must be 10 digit")
        Long accountNumber) {
}
