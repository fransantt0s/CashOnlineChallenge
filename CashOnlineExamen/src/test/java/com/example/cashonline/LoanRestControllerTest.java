package com.example.cashonline;


import com.example.cashonline.controller.LoanController;
import com.example.cashonline.dao.LoanDao;
import com.example.cashonline.model.Loan;
import com.example.cashonline.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableSpringDataWebSupport
@WebMvcTest(LoanController.class)
public class LoanRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanService loanService;

    @Test
    public void testListLoans() throws Exception {
        PageRequest pageable = PageRequest.of(1, 10);
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(7L,29000));
        loans.add(new Loan(8L,39000));
        loans.add(new Loan(9L,49000));
        loans.add(new Loan(10L,59000));
        Page <Loan> loansPage = new PageImpl<>(loans,pageable, loans.size());

        given(loanService.findAll(pageable)).willReturn(loansPage);

        String url = "/loans?page=1&size=10";
        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetLoanById() throws  Exception{
        Loan loan1 = new Loan();
        loan1.setId(1L);
        loan1.setTotal(25000);
        when(loanService.getLoanById(1L)).thenReturn(loan1);

        String url = "/loans/1";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(25000));
    }

    @Test
    public void testSaveLoan() throws Exception{
        Loan loan1 = new Loan();
        loan1.setId(1L);
        loan1.setTotal(25000);

        when(loanService.saveLoan(Mockito.any(Loan.class))).thenReturn(loan1);

        String url = "/loans";
        mockMvc.perform(MockMvcRequestBuilders.post(url).content(new ObjectMapper().writeValueAsString(loan1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(25000));

    }

    @Test
    public void testDeleteLoan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/loans/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }



}
