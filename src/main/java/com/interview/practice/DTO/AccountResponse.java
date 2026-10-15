package com.interview.practice.DTO;

import com.interview.practice.Entity.AccountStatus;
import com.interview.practice.Entity.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountResponse {

    private Long id ;
    private String accountNumber ;
    private String customerName ;
    private AccountStatus accountStatus ;
    private AccountType accountType ;
    private BigDecimal balance ;
    private LocalDateTime createdAt ;

}
