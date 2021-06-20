package com.example.cashonline.dao;

import com.example.cashonline.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDao extends JpaRepository<Loan,Long> {
}
