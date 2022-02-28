package com.example.finalexam.entity;

import com.example.finalexam.enums.AccountRoles;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 150)
    private String userName;

    @Column(length= 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private AccountRoles role;

    @NotNull
    @Column(length = 100)
    private String password;

    private Date createdDate;

    private boolean deleted;

    @ManyToOne
    private Department department;

}
