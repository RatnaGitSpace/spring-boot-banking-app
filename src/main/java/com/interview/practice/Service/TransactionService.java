package com.interview.practice.Service;

import com.interview.practice.DTO.TransactionResponse;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> getTransactionsByAccountId(Long accountId);
}
