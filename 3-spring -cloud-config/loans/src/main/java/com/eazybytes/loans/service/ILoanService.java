package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;

public interface ILoanService {

    void createLoan(final String mobileNumber);

    LoansDto getLoan(final String mobileNumber);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(final String mobileNumber);

}
