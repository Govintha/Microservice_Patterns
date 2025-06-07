package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.response.AccountsDto;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {

    private AccountsMapper(){}

    public static AccountsDto mapToAccountDto(Accounts accounts){

        return new AccountsDto(
                              accounts.getAccountType()
                              ,accounts.getBranchAddress()
                              ,accounts.getAccountNumber());

    }

    public static Accounts mapToAccount(AccountsDto accountsDto,Accounts accounts){

        accounts.setAccountNumber(accounts.getAccountNumber());
        accounts.setAccountType(accountsDto.accountType());
        accounts.setBranchAddress(accountsDto.branchAddress());
        return accounts;
    }

}
