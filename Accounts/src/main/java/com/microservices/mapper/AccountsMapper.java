package com.microservices.mapper;

import com.microservices.DTO.AccountDto;
import com.microservices.Entity.Accounts;

public class AccountsMapper {
    public static AccountDto mapToAccountDto(AccountDto accountDto, Accounts accounts) {

        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static Accounts mapToAccounts(Accounts accounts, AccountDto accountDto) {

        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}
