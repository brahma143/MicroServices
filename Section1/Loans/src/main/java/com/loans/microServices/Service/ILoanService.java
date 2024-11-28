package com.loans.microServices.Service;

import com.loans.microServices.DTO.LoansDto;

public interface ILoanService {

    public void createLoanAccount(String mobileNumber);
    public LoansDto getLoan(String mobileNumber);

    public boolean updateLoanAccount(LoansDto  loansDto);

    public boolean deleteLoanAccount(String mobileNumber);
}
