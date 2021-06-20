package com.example.cashonline;



import com.example.cashonline.controller.UserController;
import com.example.cashonline.model.Loan;
import com.example.cashonline.model.User;
import com.example.cashonline.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableSpringDataWebSupport
@WebMvcTest(UserController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private List <Loan> loans;

    @BeforeEach
    void setUp(){
        List <Loan> loans = new ArrayList<>();
        loans.add(new Loan(1L,2000));
        loans.add(new Loan(2L,24000));
    }

    @Test
    public void testListUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1L,"Franco","Santos",loans));
        users.add(new User(2L,"Jorge","Santos",loans));
        given(userService.findAll()).willReturn(users);

        String url = "/users";
        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetUserById() throws  Exception{
        User user1 = new User(1L,"Franco","Fernandez",loans);
        when(userService.getUserById(1L)).thenReturn(user1);
        String url = "/users/1";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Franco"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Fernandez"));
    }

    @Test
    public void testSaveUser() throws Exception{
        User user1 = new User(3L,"Juan","Santos",loans);

        when(userService.saveUser(Mockito.any(User.class))).thenReturn(user1);

        String url = "/users";
        mockMvc.perform(MockMvcRequestBuilders.post(url).content(new ObjectMapper().writeValueAsString(user1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Santos"));

    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
