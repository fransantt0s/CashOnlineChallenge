package com.example.cashonline.controller;

import com.example.cashonline.model.Loan;
import com.example.cashonline.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public ResponseEntity<Page<Loan>> getAllLoans(@PageableDefault(size = 10,page = 0) Pageable pageable){
        Page <Loan> loans = loanService.findAll(pageable);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable("id") Long id){
        Loan loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/loans")
    public ResponseEntity <Loan> createLoan(@RequestBody Loan loan){
        return new ResponseEntity<>(loanService.saveLoan(loan), HttpStatus.CREATED);
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable("id") Long id){
        loanService.deleteLoanById(id);
        return ResponseEntity.ok(null);
    }



}
