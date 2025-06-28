package com.eazybytes.loans.exception;

import com.eazybytes.loans.entity.Loans;

public class LoanAlreadyExistsException extends RuntimeException {

     public LoanAlreadyExistsException(String message){
         super(message);
     }


}
