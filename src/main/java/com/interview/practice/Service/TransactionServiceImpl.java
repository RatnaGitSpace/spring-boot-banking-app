package com.interview.practice.Service;

import com.interview.practice.DTO.TransactionResponse;
import com.interview.practice.Entity.Transaction;
import com.interview.practice.Repo.AccountRepository;
import com.interview.practice.Repo.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public List<TransactionResponse> getTransactionsByAccountId(Long accountId) {

        List<Transaction> transactions = transactionRepo.findByAccountId(accountId);
        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setId(transaction.getId());
            transactionResponse.setAccountId(transaction.getAccountId());
            transactionResponse.setType(transaction.getType());
            transactionResponse.setAmount(transaction.getAmount());
            transactionResponse.setBalanceAfter(transaction.getBalanceAfter());
            transactionResponse.setCreatedAt(transaction.getCreatedAt());

            transactionResponseList.add(transactionResponse);
        }
        return transactionResponseList;
    }
}
