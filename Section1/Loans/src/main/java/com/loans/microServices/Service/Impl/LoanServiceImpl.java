package com.loans.microServices.Service.Impl;

import com.loans.microServices.Constants.LoansConstants;
import com.loans.microServices.DTO.LoansDto;
import com.loans.microServices.Entity.Loans;
import com.loans.microServices.Exception.LoanAlreadyExistsException;
import com.loans.microServices.Exception.ResourceNotFoundException;
import com.loans.microServices.Mapper.LoansMapper;
import com.loans.microServices.Repository.LoanRepository;
import com.loans.microServices.Service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

     LoanRepository loanRepository;

    @Override
    public void createLoanAccount(String mobileNumber) {

        Optional<Loans> optionalLoans=loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }

        loanRepository.save(createNewAccount(mobileNumber));

    }

    public Loans createNewAccount(String mobileNumber){

        Loans loans1= new Loans();
        long loanNumbers=10000000+new Random().nextInt(900000000);
        loans1.setLoanNumber(String.valueOf(loanNumbers));
        loans1.setMobileNumber(mobileNumber);
        loans1.setLoanType(LoansConstants.HOME_LOAN);
        loans1.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans1.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        loans1.setAmountPaid(0);
        return loans1;
    }


    @Override
    public LoansDto getLoan(String mobileNumber) {
        
        Loans loans=loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber)
        );

        return LoansMapper.mapToLoanDto(new LoansDto(), loans);
    }

    @Override
    public boolean updateLoanAccount(LoansDto loansDto) {
        Loans loans =loanRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()->new ResourceNotFoundException("LoanNumber", "Loans",loansDto.getLoanNumber() )
        );
        LoansMapper.mapToLoans(loansDto, loans);
        loanRepository.save(loans);
        return true;
    }

    @Override

    public boolean deleteLoanAccount(String mobileNumber) {
          Loans loans=loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                  ()->new ResourceNotFoundException("loansNumber", "Loans", mobileNumber)
          );
          loanRepository.deleteById(loans.getLoanId());
        return true;
    }
}
