package com.eazybytes.card.card.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


public record CardsDto(
        @NotEmpty(message = "Mobile Number can not be a null or empty")
        @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
        String mobileNumber,

        @NotEmpty(message = "Card Number can not be a null or empty")
        @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
        String cardNumber,

        @NotEmpty(message = "CardType can not be a null or empty")
        String cardType,

        @Positive(message = "Total card limit should be greater than zero")
        int totalLimit,

        @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
        int amountUsed,

        @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
        int availableAmount) {

}
