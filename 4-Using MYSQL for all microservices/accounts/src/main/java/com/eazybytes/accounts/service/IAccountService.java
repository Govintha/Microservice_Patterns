package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.response.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchCustomer(String mobileNumber);

    boolean updateCustomer(CustomerDto customerDto);

    boolean deleteCutomer(String mobileNumber);

}
