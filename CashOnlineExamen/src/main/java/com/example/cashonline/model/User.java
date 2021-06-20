package com.example.cashonline.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotEmpty(message = "The name cannot be empty")
    private String firstName;

    @NotEmpty(message = "The last name cannot be empty")
    private String lastName;

    @OneToMany
    @JoinColumn (name="id_user")
    private List<Loan> loans;

    public User() {
    }

    public User(Long id, @NotEmpty(message = "The name cannot be empty") String firstName, @NotEmpty(message = "The last name cannot be empty") String lastName, List<Loan> loans) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }


}
