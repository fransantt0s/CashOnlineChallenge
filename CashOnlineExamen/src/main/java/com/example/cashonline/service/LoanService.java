package com.example.cashonline.service;


import com.example.cashonline.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanService {
    Page<Loan> findAll(Pageable pageable);
    Loan getLoanById(Long id);
    void deleteLoanById(Long id);
    Loan saveLoan(Loan loan);
    void deleteLoanByUserId(Long userId);

}
