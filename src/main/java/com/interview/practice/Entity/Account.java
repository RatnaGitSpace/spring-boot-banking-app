package com.interview.practice.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true, nullable = false)
    private String accountNumber ;

    @Column(nullable = false)
    private String customerName ;

    @Column(nullable = false)
    private BigDecimal balance ;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private LocalDateTime createdAt ;
}
