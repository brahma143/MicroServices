package com.loans.microServices.Mapper;

import com.loans.microServices.DTO.LoansDto;
import com.loans.microServices.Entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoanDto(LoansDto loansDto, Loans loans){
       loansDto.setLoanNumber(loans.getLoanNumber());
       loansDto.setMobileNumber(loans.getMobileNumber());
       loansDto.setLoanType(loans.getLoanType());
       loansDto.setAmountPaid(loans.getAmountPaid());
       loansDto.setTotalLoan(loans.getTotalLoan());
       loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }
}
