package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.response.AccountsDto;
import com.eazybytes.accounts.dto.response.CustomerDto;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {

    private CustomerMapper(){}

    public static CustomerDto mapToCustomerDto(Customer customer, AccountsDto accountsDto){

         return  new CustomerDto(customer.getEmail(), customer.getName(), customer.getMobileNumber(),accountsDto);
    }

    public static Customer mapToCustomer(CustomerDto customerDto,Customer customer){

         customer.setName(customerDto.name());
         customer.setEmail(customerDto.email());
         customer.setMobileNumber(customerDto.mobileNumber());
         return customer;

    }
}
