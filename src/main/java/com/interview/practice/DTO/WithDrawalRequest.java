package com.interview.practice.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithDrawalRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount should be greater than 0")
    private BigDecimal amount ;

}
