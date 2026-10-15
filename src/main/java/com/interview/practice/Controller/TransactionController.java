package com.interview.practice.Controller;

import com.interview.practice.DTO.TransactionResponse;
import com.interview.practice.Service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class TransactionController {

    private final TransactionService transactionService;

    public  TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable Long id) {

        List<TransactionResponse> transactionResponses = transactionService.getTransactionsByAccountId(id) ;
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponses);

    }

}
