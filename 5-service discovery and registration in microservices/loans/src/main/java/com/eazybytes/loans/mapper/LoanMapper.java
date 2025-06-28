package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import jakarta.validation.constraints.NotNull;

public class LoanMapper {

    private LoanMapper(){}

    public static LoansDto mapToLoansDto(@NotNull Loans loan){

      return new LoansDto(
                 loan.getMobileNumber(),
                 loan.getLoanNumber(),
                 loan.getLoanType(),
                 loan.getTotalLoan(),
                 loan.getAmountPaid(),
                 loan.getOutstandingAmount()
         );

    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.loanNumber());
        loans.setLoanType(loansDto.loanType());
        loans.setMobileNumber(loansDto.mobileNumber());
        loans.setTotalLoan(loansDto.totalLoan());
        loans.setAmountPaid(loansDto.amountPaid());
        loans.setOutstandingAmount(loansDto.outstandingAmount());
        return loans;
    }
}
