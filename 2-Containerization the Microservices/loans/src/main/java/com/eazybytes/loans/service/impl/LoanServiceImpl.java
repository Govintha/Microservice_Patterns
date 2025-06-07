package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constent.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoanMapper;
import com.eazybytes.loans.repository.LoansRespository;
import com.eazybytes.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final LoansRespository loansRespository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> byMobileNumber = loansRespository.findByMobileNumber(mobileNumber);
        if(byMobileNumber.isPresent()){
             throw new LoanAlreadyExistsException("Loand already exists  for this MobileNumber "+mobileNumber);
        }

        loansRespository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDto getLoan(String mobileNumber) {
        Loans loans = loansRespository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found for this MobileNumber " + mobileNumber));
        return LoanMapper.mapToLoansDto(loans);
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        boolean isUpdated=false;
        Loans loans = loansRespository.findByLoanNumber(loansDto.loanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found for this LoandNumber " + loansDto.loanNumber()));
        LoanMapper.mapToLoans(loansDto,loans);
        loansRespository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        boolean isDeleted=false;

        Loans loans = loansRespository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found for this MobileNumber " + mobileNumber));
        loansRespository.deleteById(loans.getLoanId());
        return true;
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
