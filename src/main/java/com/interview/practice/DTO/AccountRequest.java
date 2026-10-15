package com.interview.practice.DTO;

import com.interview.practice.Entity.AccountStatus;
import com.interview.practice.Entity.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName ;

    @NotNull(message = "Account Type is required")
    private AccountType accountType ;

    @NotNull(message = "Initial deposit is required")
    @DecimalMin(value ="0.0", inclusive = true, message = "Initial deposit cannot be negative")
    private BigDecimal initialDeposit ;

}
