package com.example.cashonline.service.impl;

import com.example.cashonline.dao.LoanDao;
import com.example.cashonline.model.Loan;
import com.example.cashonline.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;

    @Override
    public Page<Loan> findAll(Pageable pageable) {
        Page<Loan> loans = loanDao.findAll(pageable);
        if(loans.isEmpty()){
            throw  new RuntimeException("The list is empty");
        }
        return loans;
    }

    @Override
    public Loan getLoanById(Long id) {
        Loan loan = loanDao.findById(id).orElse(null);
        if(loan == null){
            throw new RuntimeException("The loan you are looking for does not exist");
        }
        return loan;
    }

    @Override
    public void deleteLoanById(Long id) {
        Loan loan = loanDao.findById(id).orElse(null);
        if(loan == null){
            throw  new RuntimeException("The loan you are trying to delete does not exist");
        }
        loanDao.delete(loan);
    }

    @Override
    public Loan saveLoan(Loan loan) {
        if(loan == null){
            throw new RuntimeException("The loan you are trying to save does not exist");
        }
        return loanDao.save(loan);

    }


}
