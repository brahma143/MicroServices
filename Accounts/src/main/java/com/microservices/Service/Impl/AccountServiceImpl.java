package com.microservices.Service.Impl;

import com.microservices.DTO.AccountDto;
import com.microservices.DTO.CustomerDto;
import com.microservices.Entity.Accounts;
import com.microservices.Entity.Customer;
import com.microservices.Exception.CustomerAlreadyExistsException;
import com.microservices.Exception.ResourceNotFoundException;
import com.microservices.Repository.AccountRepository;
import com.microservices.Repository.CustomerRepository;
import com.microservices.Service.IAccountService;
import com.microservices.constants.AccountsConstants;
import com.microservices.mapper.AccountsMapper;
import com.microservices.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer= CustomerMapper.mapToCustomer(new Customer(), customerDto);

        Optional<Customer> optionalCustomer =
                customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "+
                    customerDto.getMobileNumber());
        }
        customer.setCreated_at(LocalDateTime.now());
        customer.setCreated_by("Anonymos Account");
      Customer savedCustomer  = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }



    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    public Accounts createNewAccount(Customer customer){

        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber= 1000000000l + new Random().nextInt(900000000);

        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        newAccounts.setCreated_at(LocalDateTime.now());
        newAccounts.setCreated_by("Anonymous Accounts _");
        return newAccounts;

    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber)
               .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
       Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
               .orElseThrow(()-> new ResourceNotFoundException("Account","customerId",
                       customer.getCustomerId().toString()));

     CustomerDto customerDto = CustomerMapper.maptoCustomerDto(customer, new CustomerDto());
     customerDto.setAccountDto(AccountsMapper.mapToAccountDto(new AccountDto(), accounts));

        return customerDto;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto !=null ){
            Accounts accounts = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException
                            ("Account", "AccountNumber"," accountDto.getAccountNumber().toString()")
            );
            AccountsMapper.mapToAccounts(accounts, accountDto);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customer,customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer =customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException
                        ("customer","MobileNumber",mobileNumber));

    accountRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}
