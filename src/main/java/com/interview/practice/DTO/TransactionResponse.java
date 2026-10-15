package com.interview.practice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse {

    private Long id;
    private Long accountId;
    private String type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private LocalDateTime createdAt;

}
