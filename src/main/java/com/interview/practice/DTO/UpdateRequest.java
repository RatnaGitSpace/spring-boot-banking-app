package com.interview.practice.DTO;

import com.interview.practice.Entity.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName ;

    @NotNull(message = "Account Type is required")
    private AccountType accountType ;
}
