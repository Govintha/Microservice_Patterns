package com.eazybytes.accounts.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account info"
)
public record CustomerDto(

        @Schema(

                description = "Email of the Customer",example = "xyz@gamil.com"
        )
        @NotEmpty(message = "Email can not be empty")
        @Email(message = "give validate email")
        String email,

        @Schema(

                description = "Name of the Customer",example = "Govinth"
        )
        @NotEmpty(message = "Name can not be empty")
        String name,

        @Schema(

                description = "MobileNumber  of the Customer",example = "9876543215"
        )
        @Pattern(regexp = "^$|[0-9]{10}",message = "Mobile Number must be 10 digit")
        String mobileNumber,
        AccountsDto accountsDto) {
}
