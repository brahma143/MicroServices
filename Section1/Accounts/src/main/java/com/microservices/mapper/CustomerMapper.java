package com.microservices.mapper;

import com.microservices.DTO.CustomerDto;
import com.microservices.Entity.Customer;

public class CustomerMapper {

    public static CustomerDto maptoCustomerDto(Customer customer, CustomerDto customerDto){

        //CustomerDto customerDto = null;
       // customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(Customer customer, CustomerDto customerDto){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;
    }

}
